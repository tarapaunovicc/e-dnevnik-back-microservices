package fon.e_dnevnik.authservice.security.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import fon.e_dnevnik.authservice.security.config.JwtService;
import fon.e_dnevnik.authservice.security.token.TokenRepository;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String method = request.getMethod();
        final String path   = request.getServletPath();

        // 0) CORS preflight — uvek pusti
        if ("OPTIONS".equalsIgnoreCase(method)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 1) Javno dostupne rute — preskoči JWT proveru
        if (isWhitelisted(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2) Izvuci Authorization header i JWT
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        String username = null;
        try {
            username = jwtService.extractUsername(jwt);
        } catch (Exception ignored) {
            // npr. expired/invalid token — samo pusti chain bez autentikacije
            filterChain.doFilter(request, response);
            return;
        }

        // 3) Ako već NEMA autentikacije u kontekstu — proveri token i postavi je
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            boolean storedTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);

            if (storedTokenValid && jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 4) Nastavi lanac
        filterChain.doFilter(request, response);
    }

    private boolean isWhitelisted(String path) {
        // dozvoli /auth/** i /actuator/** uvek
        if (path.startsWith("/auth/") || path.startsWith("/actuator/")) return true;

        // ako i dalje koristiš staru rutu, ostavi ovo; inače obriši
        if (path.startsWith("/api/v1/auth")) return true;

        return false;
    }
}
