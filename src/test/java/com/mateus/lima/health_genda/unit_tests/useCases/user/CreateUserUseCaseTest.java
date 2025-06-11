package com.mateus.lima.health_genda.unit_tests.useCases.user;

import com.mateus.lima.health_genda.adapters.dtos.user.UserRequestDTO;
import com.mateus.lima.health_genda.application.usecases.user.CreateUserUseCase;
import com.mateus.lima.health_genda.domain.entities.User;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import com.mateus.lima.health_genda.exceptions.UserAlreadyExistsException;
import com.mateus.lima.health_genda.infrastructure.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CreateUserUseCaseTest {
    @InjectMocks
    CreateUserUseCase createUserUseCase;

    @Mock
    UserRepository userRepository;


    @Test
    @DisplayName("Should throw UserAlreadyExistsException when email is already registered")
    public void should_throw_user_already_exists_when_email_exists() {
        UserRequestDTO userRequestDto = new UserRequestDTO();
        userRequestDto.setEmail("existing@example.com");

        User existingUser = new User();
        existingUser.setEmail("existing@example.com");

        when(userRepository.findByEmail("existing@example.com"))
                .thenReturn(Optional.of(existingUser));

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            createUserUseCase.execute(userRequestDto, UserRole.PATIENT);
        });

        assertTrue(exception.getErrors().stream().anyMatch(error ->
                error.getField().equals("user exists") &&
                        error.getMessage().equals("A user with this email already exists")));

        verify(userRepository).findByEmail("existing@example.com");
        verifyNoMoreInteractions(userRepository);
    }
}
