package com.mateus.lima.health_genda.adapters.controllers.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.application.usecases.doctor.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
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

    private final FindAllDoctorUseCase findAllDoctorUseCase;

    private final UpdateDoctorUseCase updateDoctorUseCase;

    private final DeleteDoctorUseCase deleteDoctorUseCase;

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


    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<DoctorResponseDTO>> listDoctors(
            @RequestParam(required = false) Boolean isActive,
            Pageable pageable) {
        return ResponseEntity.ok(findAllDoctorUseCase.execute(isActive, pageable));
    }

    @PutMapping("/{doctorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Object> update(@PathVariable UUID doctorId, @RequestBody DoctorRequestDTO doctorRequestDTO)  {
        return ResponseEntity.ok().body(updateDoctorUseCase.execute(doctorRequestDTO,doctorId));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable UUID id)  {
             deleteDoctorUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }


}
