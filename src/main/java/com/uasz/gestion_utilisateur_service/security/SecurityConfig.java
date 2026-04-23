package com.uasz.gestion_utilisateur_service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();

                    config.setAllowCredentials(true);
                    config.setAllowedOrigins(List.of(
                            "http://localhost:5174",
                            "http://localhost:5173",
                            "http://127.0.0.1:5174",
                            "http://127.0.0.1:5173"
                    ));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                    config.setExposedHeaders(List.of("Authorization"));

                    return config;
                }))

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/utilisateurs/etudiants").hasRole("ADMINISTRATEUR")
                        .requestMatchers(HttpMethod.POST, "/api/utilisateurs/enseignants").hasRole("ADMINISTRATEUR")

                        .requestMatchers(HttpMethod.GET, "/api/utilisateurs/**")
                        .hasAnyRole("ADMINISTRATEUR", "ENSEIGNANT", "ETUDIANT")

                        .requestMatchers(HttpMethod.PUT, "/api/utilisateurs/**").hasRole("ADMINISTRATEUR")
                        .requestMatchers(HttpMethod.PATCH, "/api/utilisateurs/**").hasRole("ADMINISTRATEUR")
                        .requestMatchers(HttpMethod.DELETE, "/api/utilisateurs/**").hasRole("ADMINISTRATEUR")

                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}