package com.alexeymelekhov.apigateway.apigateway.handler;

import com.alexeymelekhov.apigateway.apigateway.dto.ErrorResponseDTO;
import com.alexeymelekhov.apigateway.apigateway.exception.UserLoginNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserLoginNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserLoginNotFoundException(UserLoginNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDTO(
                        HttpStatus.UNAUTHORIZED.value(),
                        e.getMessage()
                ));
    }
}
