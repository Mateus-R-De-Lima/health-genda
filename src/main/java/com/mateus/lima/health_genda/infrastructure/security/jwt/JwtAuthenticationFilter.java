package com.mateus.lima.health_genda.infrastructure.security.jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${security.algorithm-key}")
    private String securityKey;


  private  final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");


            if(header != null){
                var token = this.jwtTokenProvider.validate(header);

                if(token == null){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }

                request.setAttribute("id",token.getSubject());

                var roles = token.getClaim("roles").asList(Object.class);
                var grats =
                        roles.stream().map(role ->
                                new SimpleGrantedAuthority("ROLE_"+role.toString())).toList();

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(token.getSubject(),null,grats);

                SecurityContextHolder.getContext().setAuthentication(auth);

            }



        filterChain.doFilter(request, response);

    }
}
