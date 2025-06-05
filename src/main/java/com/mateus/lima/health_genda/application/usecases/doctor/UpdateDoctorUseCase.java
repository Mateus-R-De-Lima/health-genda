package com.mateus.lima.health_genda.application.usecases.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.adapters.mappers.DoctorMapper;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import com.mateus.lima.health_genda.exceptions.BadRequestException;
import com.mateus.lima.health_genda.exceptions.FieldErrorResponse;
import com.mateus.lima.health_genda.exceptions.NotFoundException;
import com.mateus.lima.health_genda.infrastructure.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateDoctorUseCase {

    private final DoctorRepository doctorRepository;


    public DoctorResponseDTO execute( DoctorRequestDTO doctorRequestDTO,UUID id){
        if (id == null) {
            throw new BadRequestException(List.of(
                    new FieldErrorResponse("id", "The ID must be provided")));
        }
        var existingDoctor = doctorRepository.findById(id).orElseThrow(() ->{
            throw new NotFoundException(List.of(
                    new FieldErrorResponse("doctor", "Doctor not found")));
        });
        var updateDoctor = doctorRepository.save(DoctorMapper.toEntity(doctorRequestDTO,existingDoctor.getUser()));

        return DoctorMapper.toResponseDTO(updateDoctor);
    }
}
