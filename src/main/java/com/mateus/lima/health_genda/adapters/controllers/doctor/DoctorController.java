package com.mateus.lima.health_genda.adapters.controllers.doctor;

import com.mateus.lima.health_genda.adapters.dtos.auth.AuthRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.application.usecases.doctor.*;
import com.mateus.lima.health_genda.exceptions.ApiErrorResponse;
import com.mateus.lima.health_genda.exceptions.BadRequestException;
import com.mateus.lima.health_genda.exceptions.NotFoundException;
import com.mateus.lima.health_genda.infrastructure.security.hendle.CustomAccessDeniedHandler;
import com.mateus.lima.health_genda.infrastructure.security.hendle.CustomAuthenticationEntryPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController implements DoctorApi {

    private final CreateDoctorUseCase createDoctorUseCase;

    private final FindDoctorByIdUseCase findDoctorByIdUseCase;

    private final FindAllDoctorUseCase findAllDoctorUseCase;

    private final UpdateDoctorUseCase updateDoctorUseCase;

    private final DeleteDoctorUseCase deleteDoctorUseCase;

    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Object> create(@PathVariable UUID userId, @RequestBody DoctorRequestDTO doctorRequestDTO)  {
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(createDoctorUseCase.execute(doctorRequestDTO,userId));
    }

    @GetMapping("/{doctorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Object> findById(@PathVariable UUID doctorId)  {
        return ResponseEntity.ok().body(findDoctorByIdUseCase.execute(doctorId));
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


    @DeleteMapping("/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable UUID doctorId)  {
             deleteDoctorUseCase.execute(doctorId);
        return ResponseEntity.noContent().build();
    }


}
