package com.mateus.lima.health_genda.adapters.mappers;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.domain.entities.Doctor;
import com.mateus.lima.health_genda.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public  static  DoctorResponseDTO toResponseDTO(Doctor doctor) {
        return DoctorResponseDTO.builder()
                .id(doctor.getId())
                .bio(doctor.getBio())
                .crm(doctor.getCrm())
                .specialty(doctor.getSpecialty())
                .phone(doctor.getPhone())
                .userId(doctor.getUser().getId())
                .build();
    }


    public static Doctor toEntity(DoctorRequestDTO dto, User user) {
        return Doctor.builder()
                .bio(dto.getBio())
                .crm(dto.getCrm())
                .specialty(dto.getSpecialty())
                .phone(dto.getPhone())
                .isActive(true)
                .user(user)
                .build();
    }
}
