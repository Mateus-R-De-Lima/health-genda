package com.mateus.lima.health_genda.adapters.controllers.users;


import com.mateus.lima.health_genda.adapters.dtos.user.UserRequestDto;
import com.mateus.lima.health_genda.application.usecases.user_doctor.CreateUserDoctorCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserDoctorCase createUserDoctorCase;

    @PostMapping("/doctor")
    public ResponseEntity<Object>  createDoctor(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(createUserDoctorCase.execute(userRequestDto));
    }
}
