package com.mateus.lima.health_genda.unit_tests.useCases.doctor;

import com.mateus.lima.health_genda.application.usecases.doctor.FindDoctorByIdUseCase;
import com.mateus.lima.health_genda.exceptions.BadRequestException;
import com.mateus.lima.health_genda.exceptions.NotFoundException;
import com.mateus.lima.health_genda.infrastructure.repositories.DoctorRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
public class FindDoctorByIdUseCaseTest {

    @InjectMocks
    FindDoctorByIdUseCase findDoctorByIdUseCase;

    @Mock
    DoctorRepository doctorRepository;


    @Test
    @DisplayName("Should not find a doctor with a non-existent ID")
    public void should_not_find_a_doctor_with_a_non_existent_id(){
        assertThrows(NotFoundException.class, () ->{
           findDoctorByIdUseCase.execute(UUID.randomUUID());
        });

    }

}
