package com.mateus.lima.health_genda.infrastructure.security;

import com.mateus.lima.health_genda.infrastructure.security.hendle.CustomAccessDeniedHandler;
import com.mateus.lima.health_genda.infrastructure.security.hendle.CustomAuthenticationEntryPoint;
import com.mateus.lima.health_genda.infrastructure.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private  final JwtAuthenticationFilter jwtAuthenticationFilter;
    private  final CustomAccessDeniedHandler customAccessDeniedHandler;
    private  final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private static final String[] PERMIT_ALL_LIST = {
            "/user/**",
            "/auth/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // desabilita CSRF
                .authorizeHttpRequests(auth -> {
                            auth.requestMatchers(PERMIT_ALL_LIST).permitAll(); // permite todos de /user/**
                            auth.anyRequest().authenticated();
                        }
                ).exceptionHandling(exception ->
                        exception
                                .authenticationEntryPoint(customAuthenticationEntryPoint) // 401 quando não autenticado
                                .accessDeniedHandler(customAccessDeniedHandler)           // 403 quando sem permissão
                ).addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)

        ;



        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
