package com.mateus.lima.health_genda.adapters.controllers.users;


import com.mateus.lima.health_genda.adapters.dtos.user.UserRequestDto;
import com.mateus.lima.health_genda.application.usecases.user_doctor.CreateUserDoctorCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserDoctorCase createUserDoctorCase;

    @PostMapping("/doctor")
    public ResponseEntity<Object>  createDoctor( @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(createUserDoctorCase.execute(userRequestDto));
    }

    @GetMapping("/doctor")
    public ResponseEntity<String> getDoctor() {
        return ResponseEntity.ok("Rota liberada!");
    }
}
