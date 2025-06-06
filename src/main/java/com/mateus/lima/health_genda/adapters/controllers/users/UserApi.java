package com.mateus.lima.health_genda.adapters.controllers.users;

import com.mateus.lima.health_genda.adapters.dtos.doctor.DoctorRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.user.UserRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.user.UserResponseDTO;
import com.mateus.lima.health_genda.domain.enums.UserRole;
import com.mateus.lima.health_genda.exceptions.ApiErrorResponse;
import com.mateus.lima.health_genda.exceptions.UserAlreadyExistsException;
import com.mateus.lima.health_genda.infrastructure.security.hendle.CustomAccessDeniedHandler;
import com.mateus.lima.health_genda.infrastructure.security.hendle.CustomAuthenticationEntryPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "The User controller is responsible for creating system users, including doctors, administrators, and patients.")
public interface UserApi {

    @Operation( summary = "Create User Doctor",description = "Endpoint responsible for creating a new user with the role of doctor in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = UserResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = CustomAuthenticationEntryPoint.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = UserAlreadyExistsException.class))
            }),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            }),

    })
     ResponseEntity<Object> createDoctor(@RequestBody UserRequestDTO userRequestDto);

    @Operation( summary = "Create User Patient",description = "Endpoint responsible for creating a new user with the role of patient in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = UserResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = CustomAuthenticationEntryPoint.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = UserAlreadyExistsException.class))
            }),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            }),

    })
     ResponseEntity<Object>  createPatient( @RequestBody UserRequestDTO userRequestDto) ;


    @Operation( summary = "Create User Admin",description = "Endpoint responsible for creating a new user with the role of admin in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = UserResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = CustomAuthenticationEntryPoint.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = UserAlreadyExistsException.class))
            }),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            }),

    })
     ResponseEntity<Object>  createAdmin( @RequestBody UserRequestDTO userRequestDto) ;

}
