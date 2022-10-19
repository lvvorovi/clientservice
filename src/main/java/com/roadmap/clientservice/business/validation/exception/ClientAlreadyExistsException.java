package com.roadmap.clientservice.business.validation.exception;

public class ClientAlreadyExistsException extends ValidationException {
    public ClientAlreadyExistsException(String message) {
        super(message);
    }

    public ClientAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
