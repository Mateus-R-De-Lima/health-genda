package com.mateus.lima.health_genda.application.usecases.doctor;


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
public class DeleteDoctorUseCase {

    private final DoctorRepository doctorRepository;

    public void execute(UUID id){

        if (id == null) {
            throw new BadRequestException(List.of(
                    new FieldErrorResponse("id", "The ID must be provided")));
        }

        var existingDoctor = doctorRepository.findById(id).orElseThrow(() ->{
            throw new NotFoundException(List.of(
                    new FieldErrorResponse("doctor", "Doctor not found")));
        });

        if (Boolean.FALSE.equals(existingDoctor.getIsActive())) {
            throw new BadRequestException(
                    List.of(new FieldErrorResponse("doctor", "The doctor is already deactivated"))
            );
        }




        existingDoctor.setIsActive(false);

        doctorRepository.save(existingDoctor);

    }


}
