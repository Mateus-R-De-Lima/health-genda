package com.mateus.lima.health_genda.adapters.dtos.doctor;

import com.mateus.lima.health_genda.domain.enums.Specialty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorRequestDTO {

    @NotBlank(message = "CRM is required.")
    @Pattern(regexp = "\\d{4,10}", message = "CRM must be between 4 and 10 numeric digits.")
    private String crm;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Specialty is required.")
    private Specialty specialty;

    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "\\d{10,11}", message = "Phone number must contain only digits (10 or 11 digits).")
    private String phone;

    @NotBlank(message = "Bio is required.")
    @Size(min = 10, max = 1000, message = "Bio must be between 10 and 1000 characters.")
    private String bio;
}
