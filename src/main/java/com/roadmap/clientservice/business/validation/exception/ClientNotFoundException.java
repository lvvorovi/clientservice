package com.roadmap.clientservice.business.validation.exception;

public class ClientNotFoundException extends ValidationException {

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
