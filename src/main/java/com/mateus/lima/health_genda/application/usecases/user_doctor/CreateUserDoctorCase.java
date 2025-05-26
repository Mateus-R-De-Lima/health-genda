package com.mateus.lima.health_genda.application.usecases.user_doctor;

import com.mateus.lima.health_genda.adapters.dtos.user.UserRequestDto;
import com.mateus.lima.health_genda.adapters.dtos.user.UserResponseDTO;
import com.mateus.lima.health_genda.domain.entities.User;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import com.mateus.lima.health_genda.exceptions.UserAlreadyExistsException;
import com.mateus.lima.health_genda.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CreateUserDoctorCase {
    @Autowired
    private  UserRepository userRepository;


    public UserResponseDTO execute(UserRequestDto userRequestDto) {

       this.userRepository.findByEmail(userRequestDto.getEmail())
                .ifPresent((user) -> {
                    throw new UserAlreadyExistsException("A user with this email already exists");
                });


        var newUser = User.builder()
                .email(userRequestDto.getEmail())
                .fullName(userRequestDto.getFullName())
                .password(userRequestDto.getPassword())
                .role(UserRole.DOCTOR)
                .isActive(true)
                .build();

        var response = this.userRepository.saveAndFlush(newUser);


        return UserResponseDTO.builder()
                .id(response.getId())
                .fullName(response.getFullName())
                .email(response.getEmail())
                .role(response.getRole())
                .build();


    }

}
