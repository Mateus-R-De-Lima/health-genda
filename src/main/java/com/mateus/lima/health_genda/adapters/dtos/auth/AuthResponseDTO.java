package com.mateus.lima.health_genda.adapters.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Builder
public record AuthResponseDTO (String acess_token, Instant expires_in) {
}
