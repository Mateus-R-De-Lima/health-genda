package com.mateus.lima.health_genda.application.usecases.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mateus.lima.health_genda.adapters.dtos.auth.AuthRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.auth.AuthResponseDTO;
import com.mateus.lima.health_genda.exceptions.BadRequestException;
import com.mateus.lima.health_genda.exceptions.UserAlreadyExistsException;
import com.mateus.lima.health_genda.infrastructure.repositories.UserRepository;
import com.mateus.lima.health_genda.infrastructure.security.jwt.JwtConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthDoctorUseCase {

    @Value("${security.algorithm-key}")
    private String securityKey;

     private  final PasswordEncoder passwordEncoder;

     private  final UserRepository userRepository;


    public AuthResponseDTO execute(AuthRequestDTO authRequestDTO) throws AuthenticationException{

        var userDoctor =
                this.userRepository.findByEmail(authRequestDTO.getEmail()).orElseThrow( () -> {
                    throw  new UsernameNotFoundException("User not found");
                });

        var passwordMaches = passwordEncoder.matches(authRequestDTO.getPassword(),userDoctor.getPassword());

        if(!passwordMaches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(securityKey);

        var expiresIn = JwtConstants.EXPIRATION_TIME;

        var token = JWT.create()
                .withIssuer("health")
                .withClaim("roles", Arrays.asList(userDoctor.getRole().toString()))
                .withExpiresAt(expiresIn)
                .withSubject(userDoctor.getId().toString())
                .sign(algorithm) ;

        return AuthResponseDTO.builder()
                .expires_in(expiresIn)
                .acess_token(token)
                .build();


    }

}
