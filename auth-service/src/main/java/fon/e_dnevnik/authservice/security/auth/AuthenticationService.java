package fon.e_dnevnik.authservice.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import fon.e_dnevnik.authservice.dao.UserRepository;
import fon.e_dnevnik.authservice.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.authservice.security.config.JwtService;
import fon.e_dnevnik.authservice.security.token.Token;
import fon.e_dnevnik.authservice.security.token.TokenRepository;
import fon.e_dnevnik.authservice.security.token.TokenType;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        revokeAllMemberTokens(user);
        saveMemberToken(user, jwtToken);

        String id = user.getUsername();
        String role = user.getRole().toString(); // ROLE_STUDENT ili ROLE_TEACHER

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .username(id)
                .role(role)
                .message("Successful login.")
                .build();
    }
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("In refresh token method.");

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userUsername;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        refreshToken = authHeader.substring(7); // Extract refresh token
        userUsername = jwtService.extractUsername(refreshToken);

        if (userUsername != null) {
            var user = userRepository.findByUsername(userUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (jwtService.isTokenValid(refreshToken, user)) {
                var newAccessToken = jwtService.generateToken(user);

                revokeAllMemberTokens(user);
                saveMemberToken(user, newAccessToken);

                var authResponse = AuthenticationResponse.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(refreshToken) // Same refresh token is returned
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired refresh token");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
    public void revokeAllMemberTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUsername());
        System.out.println(validUserTokens);
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    public void saveMemberToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
