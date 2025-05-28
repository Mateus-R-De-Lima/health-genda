package com.mateus.lima.health_genda.adapters.controllers.auth;

import com.mateus.lima.health_genda.adapters.dtos.auth.AuthRequestDTO;
import com.mateus.lima.health_genda.application.usecases.auth.AuthUseCase;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUseCase authUseCase;

    @PostMapping("/doctor")
    public ResponseEntity<Object> tokenDoctor(@RequestBody AuthRequestDTO authRequestDTO) throws AuthenticationException {
        return ResponseEntity.ok().body(authUseCase.execute(authRequestDTO, UserRole.DOCTOR));
    }

    @PostMapping("/patient")
    public ResponseEntity<Object> tokenPatient(@RequestBody AuthRequestDTO authRequestDTO) throws AuthenticationException {
        return ResponseEntity.ok().body(authUseCase.execute(authRequestDTO,UserRole.PATIENT));
    }

    @PostMapping("/admin")
    public ResponseEntity<Object> tokenAdmin(@RequestBody AuthRequestDTO authRequestDTO) throws AuthenticationException {
        return ResponseEntity.ok().body(authUseCase.execute(authRequestDTO,UserRole.ADMIN));
    }


}
