package com.mateus.lima.health_genda.application.usecases.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.adapters.mappers.DoctorMapper;
import com.mateus.lima.health_genda.exceptions.FieldErrorResponse;
import com.mateus.lima.health_genda.exceptions.NotFoundException;
import com.mateus.lima.health_genda.infrastructure.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindDoctorByIdUseCase {

    private final DoctorRepository doctorRepository;

    public DoctorResponseDTO execute(UUID id){
        var existingDoctor = doctorRepository.findById(id).orElseThrow(() ->{
            throw new   NotFoundException(List.of(
                    new FieldErrorResponse("doctor", "Doctor not found")));
        });


        return DoctorMapper.toResponseDTO(existingDoctor);

    }
}
