package com.roadmap.clientservice.business.handler;

import com.roadmap.clientservice.business.validation.exception.ValidationException;
import com.roadmap.clientservice.model.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

import static com.roadmap.clientservice.business.LogMessageStore.VALIDATION_FAILED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String exceptionMessage = ex.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.warn(VALIDATION_FAILED + exceptionMessage);
        ErrorDto errorDto = new ErrorDto(
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                exceptionMessage);
        return ResponseEntity
                .status(BAD_REQUEST)
                .contentType(APPLICATION_JSON)
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleClientNotFoundException(ValidationException ex) {
        log.warn(VALIDATION_FAILED + ex.getMessage());
        ErrorDto errorDto = new ErrorDto(
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                ex.getMessage());
        return ResponseEntity
                .status(BAD_REQUEST)
                .contentType(APPLICATION_JSON)
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleError(Error ex) {
        log.error(ex.getMessage(), ex);
        ErrorDto errorDto = new ErrorDto(
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .contentType(APPLICATION_JSON)
                .body(errorDto);
    }

}
