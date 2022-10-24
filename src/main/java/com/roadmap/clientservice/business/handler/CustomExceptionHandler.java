package com.roadmap.clientservice.business.handler;

import com.roadmap.clientservice.business.validation.exception.ValidationException;
import com.roadmap.clientservice.model.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

import static com.roadmap.clientservice.business.LogMessageStore.VALIDATION_FAILED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                          HttpServletRequest request) {
        String exceptionMessage = ex.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorDto errorDto = new ErrorDto(
                BAD_REQUEST,
                exceptionMessage,
                request);

        log.warn(VALIDATION_FAILED + errorDto);
        return ResponseEntity
                .status(BAD_REQUEST)
                .contentType(APPLICATION_JSON)
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleClientNotFoundException(ValidationException ex,
                                                                  HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                BAD_REQUEST,
                ex.getMessage(),
                request);

        log.warn(VALIDATION_FAILED + errorDto);
        return ResponseEntity
                .status(BAD_REQUEST)
                .contentType(APPLICATION_JSON)
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleError(Error ex,
                                                HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request);

        log.error(VALIDATION_FAILED + errorDto);
        ex.printStackTrace();
        return ResponseEntity
                .internalServerError()
                .contentType(APPLICATION_JSON)
                .body(errorDto);
    }

}
