package com.mateus.lima.health_genda.application.usecases.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.adapters.mappers.DoctorMapper;
import com.mateus.lima.health_genda.infrastructure.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllDoctorUseCase {

    private final DoctorRepository doctorRepository;


    public Page<DoctorResponseDTO> execute(Boolean isActive, Pageable pageable) {

        if(isActive == null){
        return  doctorRepository.findAll(pageable)
                    .map(DoctorMapper::toResponseDTO);
        }
        return doctorRepository.findByIsActive(isActive, pageable)
                .map(DoctorMapper::toResponseDTO);
    }

}
