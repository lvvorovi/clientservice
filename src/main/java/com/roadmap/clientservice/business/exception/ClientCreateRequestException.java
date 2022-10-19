package com.roadmap.clientservice.business.exception;

public class ClientCreateRequestException extends RuntimeException {
    public ClientCreateRequestException(String message) {
        super(message);
    }
}
