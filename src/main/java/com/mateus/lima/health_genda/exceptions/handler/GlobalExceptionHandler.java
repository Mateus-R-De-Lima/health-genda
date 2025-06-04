package com.mateus.lima.health_genda.exceptions.handler;

import com.mateus.lima.health_genda.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldErrorResponse> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String mensagem = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
            fieldErrorResponse.setField(err.getField()); // melhor usar o campo real com erro
            fieldErrorResponse.setMessage(mensagem);
            errors.add(fieldErrorResponse);
        });

        ApiErrorResponse response = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(BadRequestException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Bad Request");
        error.put("details", ex.getErrors());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Not Found");
        error.put("details", ex.getErrors()); 
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>>  handleUserAlreadyExists(UserAlreadyExistsException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Not Found");
        error.put("details", ex.getErrors()); ; // 409 = Conflict
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
