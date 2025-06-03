package com.mateus.lima.health_genda.adapters.controllers.users;


import com.mateus.lima.health_genda.adapters.dtos.user.UserRequestDTO;
import com.mateus.lima.health_genda.application.usecases.user.CreateUserUseCase;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/doctor")
    public ResponseEntity<Object>  createDoctor( @RequestBody UserRequestDTO userRequestDto) {
        return ResponseEntity.ok().body(createUserUseCase.execute(userRequestDto, UserRole.DOCTOR));
    }

    @PostMapping("/patient")
    public ResponseEntity<Object>  createPatient( @RequestBody UserRequestDTO userRequestDto) {
        return ResponseEntity.ok().body(createUserUseCase.execute(userRequestDto,UserRole.PATIENT));
    }

    @PostMapping("/admin")
    public ResponseEntity<Object>  createAdmin( @RequestBody UserRequestDTO userRequestDto) {
        return ResponseEntity.ok().body(createUserUseCase.execute(userRequestDto,UserRole.ADMIN));
    }
}
