package com.mateus.lima.health_genda.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    private int statusCode;
    private List<FieldErrorResponse> error;
}
