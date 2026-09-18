package com.alexeymelekhov.apigateway.apigateway.exception;

public class UserLoginNotFoundException extends RuntimeException {
    public UserLoginNotFoundException(String message) {
        super(message);
    }
}
