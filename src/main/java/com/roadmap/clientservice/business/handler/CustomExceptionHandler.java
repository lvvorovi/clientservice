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

import static com.roadmap.clientservice.business.LogMessageStore.VALIDATION_FAILED;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    private final MediaType jsonMediaType = MediaType.APPLICATION_JSON;

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDto errorDto = new ErrorDto(status.value(), status.getReasonPhrase(), message);
        log.warn(VALIDATION_FAILED + errorDto);
        return ResponseEntity
                .status(status)
                .contentType(jsonMediaType)
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleClientNotFoundException(ValidationException ex) {
        log.warn(VALIDATION_FAILED + ex.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDto errorDto = new ErrorDto(status.value(), status.getReasonPhrase(), ex.getMessage());
        return ResponseEntity
                .status(status)
                .contentType(jsonMediaType)
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleError(Error ex) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDto errorDto = new ErrorDto(status.value(), status.getReasonPhrase(), ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .contentType(jsonMediaType)
                .body(errorDto);
    }

}
