package fon.e_dnevnik.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/users/me","/teachers/**","/students/**").permitAll()
//                        .anyRequest().permitAll()
//                );
//        return http.build();
//    }
//}
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> {}) // <<— BITNO: uključi CORS filter
//                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        // pusti preflight OPTIONS svuda
//                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
//                        // tvoje rute:
//                        .requestMatchers("/users/me","/teachers/**","/students/**").permitAll()
//                        .anyRequest().permitAll()
//                );
//        return http.build();
//    }
//
//    @Bean
//    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
//        var config = new org.springframework.web.cors.CorsConfiguration();
//        config.setAllowedOrigins(java.util.List.of("http://localhost:4200")); // Angular dev
//        config.setAllowedMethods(java.util.List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
//        config.setAllowedHeaders(java.util.List.of("Authorization","Content-Type","X-Requested-With"));
//        // ako koristiš cookies/withCredentials u Angularu, uključi sledeće dve linije:
//        // config.setAllowCredentials(true);
//        // (ne smeš koristiti "*" za origin ako je allowCredentials=true)
//
//        var source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
//}
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/users/me", "/teachers/**", "/students/**").permitAll()
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
