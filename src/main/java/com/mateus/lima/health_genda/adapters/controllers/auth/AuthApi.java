package com.mateus.lima.health_genda.adapters.controllers.auth;

import com.mateus.lima.health_genda.adapters.dtos.auth.AuthRequestDTO;
import com.mateus.lima.health_genda.adapters.dtos.auth.AuthResponseDTO;
import com.mateus.lima.health_genda.infrastructure.security.hendle.CustomAccessDeniedHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.AuthenticationException;

@Tag(name = "Auth",description = "Endpoint responsible for generating the JWT token that will be used for authentication on the other routes.")
public interface AuthApi {

    @Operation( summary = "Token Doctor",description = "Endpoint responsible for generating the authentication token for user of type doctor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AuthResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = AuthenticationException.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = CustomAccessDeniedHandler.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = UsernameNotFoundException.class))
            })
    })
    ResponseEntity<Object> tokenDoctor(@RequestBody AuthRequestDTO authRequestDTO) throws AuthenticationException;


    @Operation( summary = "Token Patient",description = "Endpoint responsible for generating the authentication token for user of type patient")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AuthResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = AuthenticationException.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = CustomAccessDeniedHandler.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = UsernameNotFoundException.class))
            })
    })
     ResponseEntity<Object> tokenPatient(@RequestBody AuthRequestDTO authRequestDTO) throws AuthenticationException;



    @Operation( summary = "Token Admin",description = "Endpoint responsible for generating the authentication token for user of type admin")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AuthResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = AuthenticationException.class))
            }),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = CustomAccessDeniedHandler.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = UsernameNotFoundException.class))
            })
    })
     ResponseEntity<Object> tokenAdmin(@RequestBody AuthRequestDTO authRequestDTO) throws AuthenticationException;

}
