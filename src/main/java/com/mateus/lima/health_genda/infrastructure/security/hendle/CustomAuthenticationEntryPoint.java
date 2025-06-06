package com.mateus.lima.health_genda.infrastructure.security.hendle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    //TODO: Ajustar o retorno para o padrão de resposta.
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Authentication is required to access this resource.\"}");
    }
}