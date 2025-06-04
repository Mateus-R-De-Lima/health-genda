package com.mateus.lima.health_genda.application.usecases.user;

import com.mateus.lima.health_genda.adapters.dtos.user.UserRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.user.UserResponseDTO;
import com.mateus.lima.health_genda.domain.entities.User;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import com.mateus.lima.health_genda.exceptions.FieldErrorResponse;
import com.mateus.lima.health_genda.exceptions.UserAlreadyExistsException;
import com.mateus.lima.health_genda.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private  final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO execute(UserRequestDTO userRequestDto, UserRole role) {

       this.userRepository.findByEmail(userRequestDto.getEmail())
                .ifPresent((user) -> {
                    throw new UserAlreadyExistsException(List.of(
                            new FieldErrorResponse("user exists","A user with this email already exists")));
                });


       var password = passwordEncoder.encode(userRequestDto.getPassword());

        var newUser = User.builder()
                .email(userRequestDto.getEmail())
                .fullName(userRequestDto.getFullName())
                .password(password)
                .role(role)
                .isActive(true)
                .build();

        var response = this.userRepository.save(newUser);


        return UserResponseDTO.builder()
                .id(response.getId())
                .fullName(response.getFullName())
                .email(response.getEmail())
                .role(response.getRole())
                .build();


    }

}
