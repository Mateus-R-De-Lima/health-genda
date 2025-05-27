package com.mateus.lima.health_genda.infrastructure.security.jwt;

import java.time.Duration;
import java.time.Instant;

public class JwtConstants {

    public static  final  String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final Instant EXPIRATION_TIME = Instant.now().plus(Duration.ofHours(2));

    public static final String ROLE_CLAIM = "role";
}
