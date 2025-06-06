package com.mateus.lima.health_genda.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider {

    @Value("${security.algorithm-key}")
    private String securityKey;


    public DecodedJWT validate(String token){

        token = token.replace("Bearer ","");

        Algorithm algorithm = Algorithm.HMAC256(securityKey);

        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token);

        }catch (JWTVerificationException ex){
            //TODO: Melhorar o tratamento
            return null;
        }

    }

}
