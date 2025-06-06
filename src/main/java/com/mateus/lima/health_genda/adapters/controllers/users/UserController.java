package com.mateus.lima.health_genda.adapters.controllers.users;


import com.mateus.lima.health_genda.adapters.dtos.user.UserRequestDTO;
import com.mateus.lima.health_genda.application.usecases.user.CreateUserUseCase;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController  implements UserApi{

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/doctor")
    public ResponseEntity<Object>  createDoctor( @RequestBody UserRequestDTO userRequestDto) {
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(createUserUseCase.execute(userRequestDto, UserRole.DOCTOR));
    }

    @PostMapping("/patient")
    public ResponseEntity<Object>  createPatient( @RequestBody UserRequestDTO userRequestDto) {
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(createUserUseCase.execute(userRequestDto,UserRole.PATIENT));
    }

    @PostMapping("/admin")
    public ResponseEntity<Object>  createAdmin( @RequestBody UserRequestDTO userRequestDto) {
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(createUserUseCase.execute(userRequestDto,UserRole.ADMIN));
    }
}
