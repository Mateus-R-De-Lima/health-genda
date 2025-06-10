package com.mateus.lima.health_genda.unit_tests.useCases.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.application.usecases.doctor.CreateDoctorUseCase;
import com.mateus.lima.health_genda.domain.entities.Doctor;
import com.mateus.lima.health_genda.domain.entities.User;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import com.mateus.lima.health_genda.exceptions.BadRequestException;
import com.mateus.lima.health_genda.exceptions.NotFoundException;
import com.mateus.lima.health_genda.infrastructure.repositories.DoctorRepository;
import com.mateus.lima.health_genda.infrastructure.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CreateDoctorUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private CreateDoctorUseCase createDoctorUseCase;

    @Test
    @DisplayName("Should not be able to create a doctor without providing a user ID.")
    public void shouldNotBeAbleToCreateDoctorWithoutUserId() {
             assertThrows(BadRequestException.class, () -> {
            createDoctorUseCase.execute(null, null);
        });
    }


    @Test
    @DisplayName("Should not be able to create a doctor with a non-existent user ID.")
    public void should_not_be_able_to_create_a_doctor_with_a_non_existent_user_id(){
        assertThrows(NotFoundException.class,()->{
            createDoctorUseCase.execute(null,UUID.randomUUID());
        });
    }


    @Test
    @DisplayName("Should not be able to create a doctor if the user ID is already linked to another doctor.")
    public void should_not_be_able_to_create_a_doctor_if_the_user_id_is_already_linked_to_another_doctor(){
        var userId = UUID.randomUUID();
        var user = new User();
        user.setId(userId);

        var doctor = new Doctor();
        doctor.setId(UUID.randomUUID());
        doctor.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(doctorRepository.findByUserId(userId)).thenReturn(Optional.of(doctor));

        assertThrows(BadRequestException.class, () -> {
            createDoctorUseCase.execute(null, userId);
        });
    }


    @Test
    @DisplayName("Should not be able to create a doctor if the user does not have the DOCTOR role")
    public void should_not_create_doctor_if_user_has_invalid_role() {

        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setRole(UserRole.PATIENT);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(doctorRepository.findByUserId(userId)).thenReturn(Optional.empty());


        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            createDoctorUseCase.execute(new DoctorRequestDTO(), userId);
        });


        assertTrue(exception.getErrors().stream()
                .anyMatch(error -> error.getField().equals("role") &&
                        error.getMessage().contains("permission")));
    }

}
