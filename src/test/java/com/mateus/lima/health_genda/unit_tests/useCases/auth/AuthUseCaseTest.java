package com.mateus.lima.health_genda.unit_tests.useCases.auth;

import com.mateus.lima.health_genda.adapters.dtos.auth.AuthRequestDTO;
import com.mateus.lima.health_genda.application.usecases.auth.AuthUseCase;
import com.mateus.lima.health_genda.domain.entities.User;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import com.mateus.lima.health_genda.infrastructure.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import javax.naming.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AuthUseCaseTest {
    @InjectMocks
    AuthUseCase authUseCase;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("Should throw UsernameNotFoundException when user is not found by email")
    public void should_throw_username_not_found_exception_when_user_not_found() {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO("notfound@example.com", "anyPassword");

        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            authUseCase.execute(authRequestDTO, UserRole.ADMIN);
        });

        verify(userRepository).findByEmail("notfound@example.com");
        verifyNoMoreInteractions(userRepository);
    }


    @Test
    @DisplayName("Should throw AuthenticationException when password does not match")
    public void should_throw_authentication_exception_when_password_invalid() {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO("user@example.com", "wrongPassword");

        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword("hashedCorrectPassword");

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", "hashedCorrectPassword")).thenReturn(false);

        assertThrows(AuthenticationException.class, () -> {
            authUseCase.execute(authRequestDTO,UserRole.ADMIN);
        });

        verify(userRepository).findByEmail("user@example.com");
        verify(passwordEncoder).matches("wrongPassword", "hashedCorrectPassword");
        verifyNoMoreInteractions(userRepository);
    }
}
