package com.roadmap.clientservice.business.handler;

import com.roadmap.clientservice.business.exception.ClientNotFoundException;
import com.roadmap.clientservice.model.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ErrorDto errorDto = new ErrorDto(message);
        log.info("Error caught: {}", errorDto);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleClientNotFoundException(ClientNotFoundException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        log.info("Error caught: {}", errorDto);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDto);
    }

}
