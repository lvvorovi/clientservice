package com.roadmap.clientservice.model;

import com.roadmap.clientservice.business.validation.exception.message.ValidationExceptionMessage;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Validated
@NotNull
public class ClientUpdateRequest {

    @NotNull(message = ValidationExceptionMessage.FIELD_NOT_NULL)
    @NumberFormat
    private Long id;

    @NotBlank(message = ValidationExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ValidationExceptionMessage.FIELD_MAX_LENGTH_45)
    private String firstName;

    @NotBlank(message = ValidationExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ValidationExceptionMessage.FIELD_MAX_LENGTH_45)
    private String lastName;

    @NotNull(message = ValidationExceptionMessage.FIELD_NOT_NULL)
    @PastOrPresent(message = ValidationExceptionMessage.FIELD_PAST_OR_PRESENT)
    private LocalDate dateOfBirth;

    @NotBlank(message = ValidationExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ValidationExceptionMessage.FIELD_MAX_LENGTH_45)
    private String passportNumber;

    @NotBlank(message = ValidationExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ValidationExceptionMessage.FIELD_MAX_LENGTH_45)
    private String personalNumber;

    @NotBlank(message = ValidationExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ValidationExceptionMessage.FIELD_MAX_LENGTH_45)
    private String phoneNumber;

    @NotBlank(message = ValidationExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ValidationExceptionMessage.FIELD_MAX_LENGTH_45)
    private String occupation;

    @NotNull(message = ValidationExceptionMessage.FIELD_NOT_NULL)
    @Digits(integer = 25, fraction = 2, message = ValidationExceptionMessage.FIELD_MAX_DIGITS_25_2)
    private BigDecimal expenses;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime created;

}
