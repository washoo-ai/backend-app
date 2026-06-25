package com.bufete.app.config;


import com.bufete.app.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // 🌐 CORS GLOBAL
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔥 DESACTIVAR CSRF (API REST JWT)
            .csrf(csrf -> csrf.disable())

            // 🌐 CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // 🔐 REGLAS DE ACCESO
            .authorizeHttpRequests(auth -> auth

                // ✅ AUTH LIBRE
                .requestMatchers("/api/auth/**").permitAll()

                // ✅ PRE-FLIGHT CORS
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // (opcional) DELETE eventos libre si lo necesitas
                .requestMatchers(HttpMethod.DELETE, "/api/eventos/**").permitAll()

                // 🔒 TODO LO DEMÁS PROTEGIDO
                .anyRequest().authenticated()
            )

            // 🧠 JWT FILTER
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

            // 🚀 API SIN SESIÓN
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }
}