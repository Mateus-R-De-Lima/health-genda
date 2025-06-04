package com.mateus.lima.health_genda.exceptions;

import lombok.Data;


import java.util.List;

@Data
public class NotFoundException extends RuntimeException {
    private final List<FieldErrorResponse> errors;

    public NotFoundException(List<FieldErrorResponse> errors) {
        super("Erro de validação");
        this.errors = errors;
    }
}
