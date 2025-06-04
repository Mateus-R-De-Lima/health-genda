package com.mateus.lima.health_genda.adapters.controllers.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.application.usecases.doctor.CreateDoctorUseCase;
import com.mateus.lima.health_genda.application.usecases.doctor.FindDoctorByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final CreateDoctorUseCase createDoctorUseCase;

    private final FindDoctorByIdUseCase findDoctorByIdUseCase;


    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")

    public ResponseEntity<Object> create(@PathVariable UUID userId, @RequestBody DoctorRequestDTO doctorRequestDTO)  {
        return ResponseEntity.ok().body(createDoctorUseCase.execute(doctorRequestDTO,userId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Object> findById(@PathVariable UUID id)  {
        return ResponseEntity.ok().body(findDoctorByIdUseCase.execute(id));
    }





}
