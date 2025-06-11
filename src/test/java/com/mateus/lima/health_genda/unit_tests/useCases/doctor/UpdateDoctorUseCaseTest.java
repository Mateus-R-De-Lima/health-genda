package com.mateus.lima.health_genda.unit_tests.useCases.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.adapters.mappers.DoctorMapper;
import com.mateus.lima.health_genda.application.usecases.doctor.UpdateDoctorUseCase;
import com.mateus.lima.health_genda.domain.entities.Doctor;
import com.mateus.lima.health_genda.domain.entities.User;
import com.mateus.lima.health_genda.exceptions.BadRequestException;
import com.mateus.lima.health_genda.exceptions.NotFoundException;
import com.mateus.lima.health_genda.infrastructure.repositories.DoctorRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UpdateDoctorUseCaseTest {
    @InjectMocks
    UpdateDoctorUseCase updateDoctorUseCase;

    @Mock
    DoctorRepository doctorRepository;




    @Test
    @DisplayName("Should throw BadRequestException when ID is null")
    public void should_throw_bad_request_when_id_is_null() {
        DoctorRequestDTO doctorRequestDTO = new DoctorRequestDTO(); // Preencha com dados válidos se necessário

        assertThrows(BadRequestException.class, () -> {
            updateDoctorUseCase.execute(doctorRequestDTO, null);
        });

        verifyNoInteractions(doctorRepository); // Garante que o repo não foi chamado
    }


    @Test
    @DisplayName("Should throw NotFoundException when doctor is not found")
    public void should_throw_not_found_when_doctor_not_found() {
        UUID id = UUID.randomUUID();
        DoctorRequestDTO doctorRequestDTO = new DoctorRequestDTO(); // Preencha se necessário

        when(doctorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            updateDoctorUseCase.execute(doctorRequestDTO, id);
        });

        verify(doctorRepository).findById(id);
        verifyNoMoreInteractions(doctorRepository);
    }

}
