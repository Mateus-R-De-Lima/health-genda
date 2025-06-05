package com.mateus.lima.health_genda.application.usecases.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.adapters.mappers.DoctorMapper;
import com.mateus.lima.health_genda.domain.entities.Doctor;
import com.mateus.lima.health_genda.domain.entities.User;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import com.mateus.lima.health_genda.exceptions.BadRequestException;
import com.mateus.lima.health_genda.exceptions.FieldErrorResponse;
import com.mateus.lima.health_genda.exceptions.NotFoundException;
import com.mateus.lima.health_genda.infrastructure.repositories.DoctorRepository;
import com.mateus.lima.health_genda.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateDoctorUseCase {

    private  final UserRepository userRepository;
    private  final DoctorRepository doctorRepository;

    public DoctorResponseDTO execute(DoctorRequestDTO doctorRequestDTO, UUID userId)  {

        if (userId == null) {
            throw new BadRequestException(List.of(
                    new FieldErrorResponse("id", "The ID must be provided")));
        }

        var user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(List.of(
                        new FieldErrorResponse("userId", "User with ID " + userId + " not found"))));


        doctorRepository.findByUserId(userId).ifPresent((doctor) ->{
            throw new BadRequestException(List.of(
                    new FieldErrorResponse("userId", "The ID must be provided")));
        });
        if (!UserRole.DOCTOR.equals(user.getRole())) {
            throw new BadRequestException(List.of(
                    new FieldErrorResponse("role", "User does not have permission to be a doctor")));
        }


        var doctor = DoctorMapper.toEntity(doctorRequestDTO,user);

        var newDoctor = this.doctorRepository.save(doctor);

        return  DoctorMapper.toResponseDTO(newDoctor);

    }

}
