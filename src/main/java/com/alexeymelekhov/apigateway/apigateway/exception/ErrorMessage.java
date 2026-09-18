package com.alexeymelekhov.apigateway.apigateway.exception;

public enum ErrorMessage {

    USER_LOGIN_MISSING("User login is missing"),
    INTERNAL_SERVER_ERROR("Internal server error");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
