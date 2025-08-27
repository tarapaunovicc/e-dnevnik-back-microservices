package fon.e_dnevnik.attendanceservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class GatewayValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path   = request.getRequestURI();
        String method = request.getMethod();

        if ("OPTIONS".equalsIgnoreCase(method) || path.startsWith("/error")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("X-Gateway-Auth");

        if (!"valid".equals(header)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Forbidden: missing or invalid X-Gateway-Auth\"}");
            response.getWriter().flush();
            return;
        }

        filterChain.doFilter(request, response);
    }
}