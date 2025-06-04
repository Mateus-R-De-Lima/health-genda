package com.mateus.lima.health_genda.application.usecases.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.infrastructure.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllDoctorUseCase {

    private final DoctorRepository doctorRepository;


    public List<DoctorResponseDTO> execute() {
        return doctorRepository.findAll().stream()
                .map(doctor -> DoctorResponseDTO.builder()
                        .id(doctor.getId())
                        .phone(doctor.getPhone())
                        .crm(doctor.getCrm())
                        .bio(doctor.getBio())
                        .userId(doctor.getUser().getId())
                        .specialty(doctor.getSpecialty())
                        .build())
                .collect(Collectors.toList());
    }

}
