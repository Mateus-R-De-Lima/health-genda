package com.mateus.lima.health_genda.exceptions;

import lombok.Data;

import java.util.List;

@Data

public class BadRequestException extends RuntimeException{
    private final List<FieldErrorResponse> errors;

    public BadRequestException(List<FieldErrorResponse> errors) {
        super("Erro de validação");
        this.errors = errors;
    }

}
