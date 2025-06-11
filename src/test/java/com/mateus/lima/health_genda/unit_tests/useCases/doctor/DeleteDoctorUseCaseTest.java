package com.mateus.lima.health_genda.unit_tests.useCases.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.application.usecases.doctor.DeleteDoctorUseCase;
import com.mateus.lima.health_genda.application.usecases.doctor.UpdateDoctorUseCase;
import com.mateus.lima.health_genda.domain.entities.Doctor;
import com.mateus.lima.health_genda.domain.entities.User;
import com.mateus.lima.health_genda.exceptions.BadRequestException;
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
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class DeleteDoctorUseCaseTest {
    @InjectMocks
    DeleteDoctorUseCase deleteDoctorUseCase;

    @Mock
    DoctorRepository doctorRepository;


    @Test
    @DisplayName("Should throw BadRequestException when doctor is already deactivated")
    public void should_throw_bad_request_when_doctor_is_deactivated() {
        UUID id = UUID.randomUUID();

        User user = new User(); // simule um usuário
        Doctor doctor = new Doctor();
        doctor.setUser(user); // garanta que não seja null
        doctor.setIsActive(false); // simula o médico desativado

        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));

       assertThrows(BadRequestException.class, () -> {
           deleteDoctorUseCase.execute(id);
        });

        verify(doctorRepository).findById(id);
        verifyNoMoreInteractions(doctorRepository);
    }

}
