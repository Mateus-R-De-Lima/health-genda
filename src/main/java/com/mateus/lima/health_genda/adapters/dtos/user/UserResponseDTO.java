package com.mateus.lima.health_genda.adapters.dtos.user;

import com.mateus.lima.health_genda.domain.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Builder
public record UserResponseDTO (UUID id, String fullName, String email, UserRole role){

}
