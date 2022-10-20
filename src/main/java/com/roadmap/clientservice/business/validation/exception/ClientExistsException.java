package com.roadmap.clientservice.business.validation.exception;

public class ClientExistsException extends ValidationException {
    public ClientExistsException(String message) {
        super(message);
    }

    public ClientExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
