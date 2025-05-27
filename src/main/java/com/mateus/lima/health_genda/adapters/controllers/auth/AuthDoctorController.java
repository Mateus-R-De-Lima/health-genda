package com.mateus.lima.health_genda.adapters.controllers.auth;

import com.mateus.lima.health_genda.adapters.dtos.auth.AuthRequestDTO;
import com.mateus.lima.health_genda.application.usecases.auth.AuthDoctorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthDoctorController {
    private final AuthDoctorUseCase authDoctorUseCase;

    @PostMapping("/doctor")
    public ResponseEntity<Object> tokenDoctor(@RequestBody AuthRequestDTO authRequestDTO) throws AuthenticationException {
        return ResponseEntity.ok().body(authDoctorUseCase.execute(authRequestDTO));
    }

}
