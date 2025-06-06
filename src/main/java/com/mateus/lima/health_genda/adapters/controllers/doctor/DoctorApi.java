package com.mateus.lima.health_genda.adapters.controllers.doctor;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorResponseDTO;
import com.mateus.lima.health_genda.exceptions.ApiErrorResponse;
import com.mateus.lima.health_genda.infrastructure.security.hendle.CustomAccessDeniedHandler;
import com.mateus.lima.health_genda.infrastructure.security.hendle.CustomAuthenticationEntryPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Tag(name = "Doctor", description = "The Doctor controller is responsible for handling all operations that can be performed by a doctor or an administrator, including creating, updating, viewing, and deleting doctor-related data.")
public interface DoctorApi {
    @Operation( summary = "Create Doctor",description = "Endpoint responsible for creating doctor data.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = DoctorResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = CustomAuthenticationEntryPoint.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = CustomAccessDeniedHandler.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
     ResponseEntity<Object> create(@PathVariable UUID userId, @RequestBody DoctorRequestDTO doctorRequestDTO);



    @Operation( summary = "Find By Doctor",description = "Retrieve a doctor's data by their unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = DoctorResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = CustomAuthenticationEntryPoint.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = CustomAccessDeniedHandler.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
     ResponseEntity<Object> findById(@PathVariable UUID id) ;


    @Operation( summary = "Find All Doctor",description = "Retrieve all doctor records, with an optional filter by active or inactive status.")
    @ApiResponses({
            //TODO: Encontra uma maneira de melhorar a documentação, para mostra os dados do conteudo.
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Page.class)))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = CustomAuthenticationEntryPoint.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = CustomAccessDeniedHandler.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
     ResponseEntity<Page<DoctorResponseDTO>> listDoctors(
            @RequestParam(required = false) Boolean isActive,
            Pageable pageable);

    @Operation( summary = "Update Doctor",description = "Endpoint responsible for updating an existing doctor's information.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = DoctorResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = CustomAuthenticationEntryPoint.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = CustomAccessDeniedHandler.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
     ResponseEntity<Object> update(@PathVariable UUID doctorId, @RequestBody DoctorRequestDTO doctorRequestDTO) ;

    @Operation( summary = "Delete Doctor",description = "The DELETE endpoint is used to change a doctor's status to inactive, performing a logical (soft) delete.")
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = CustomAuthenticationEntryPoint.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = CustomAccessDeniedHandler.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            })
    })
     ResponseEntity<Object> delete(@PathVariable UUID id) ;

}
