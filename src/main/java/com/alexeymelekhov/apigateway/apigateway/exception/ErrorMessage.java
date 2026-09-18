package com.alexeymelekhov.apigateway.apigateway.exception;

public enum ErrorMessage {

    USER_LOGIN_MISSING("User login is missing");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
