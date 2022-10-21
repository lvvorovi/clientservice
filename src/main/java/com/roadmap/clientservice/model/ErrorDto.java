package com.roadmap.clientservice.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Data
public class ErrorDto {

    private final int code;
    private final String error;
    private final String message;
    private LocalDateTime timeStamp;

    public ErrorDto(int code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
