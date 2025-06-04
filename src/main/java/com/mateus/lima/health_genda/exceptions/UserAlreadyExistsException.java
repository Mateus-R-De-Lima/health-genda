package com.mateus.lima.health_genda.exceptions;

import lombok.Data;

import java.util.List;
@Data
public class UserAlreadyExistsException extends RuntimeException {
    private final List<FieldErrorResponse> errors;
    public UserAlreadyExistsException(List<FieldErrorResponse> errors) {
        super("Erro de validação");
        this.errors = errors;
    }
}