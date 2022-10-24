package com.roadmap.clientservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
@Getter
public class ErrorDto {

    private final int code;
    private final String error;
    private final String message;
    private final LocalDateTime timeStamp;
    private final String uri;
    private final String method;

    public ErrorDto(HttpStatus status, String message, HttpServletRequest request) {
        this.code = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.uri = request.getRequestURI();
        this.method = request.getMethod();
        this.timeStamp = LocalDateTime.now();
    }
}
