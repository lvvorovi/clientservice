package com.roadmap.clientservice.business.handler;

import com.roadmap.clientservice.business.validation.exception.ValidationException;
import com.roadmap.clientservice.model.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

import static com.roadmap.clientservice.business.validation.exception.message.ValidationExceptionMessage.VALIDATION_FAILED;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ErrorDto errorDto = new ErrorDto(message);
        log.warn(VALIDATION_FAILED + errorDto);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleClientNotFoundException(ValidationException ex) {
        log.warn(VALIDATION_FAILED + ex.getMessage());
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleError(Error ex) {
        log.error(ex.getMessage(), ex);
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDto);
    }

}
