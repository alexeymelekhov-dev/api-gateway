package com.alexeymelekhov.apigateway.apigateway.dto;

public record ErrorResponseDTO(
        int status,
        String message
) {
}
