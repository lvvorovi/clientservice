package com.roadmap.clientservice.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Data
@Validated
public class ErrorDto {

    private final String message;
    private LocalDateTime caughtAt;

    public ErrorDto(String message) {
        this.message = message;
        this.caughtAt = LocalDateTime.now();
    }
}
