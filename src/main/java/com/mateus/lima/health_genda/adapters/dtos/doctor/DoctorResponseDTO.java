package com.mateus.lima.health_genda.adapters.dtos.doctor;

import com.mateus.lima.health_genda.domain.entities.User;
import com.mateus.lima.health_genda.domain.enums.Specialty;
import lombok.Builder;

import java.util.UUID;

@Builder
public record DoctorResponseDTO (UUID id, String crm, Specialty specialty, String bio, String phone, UUID userId) {
}
