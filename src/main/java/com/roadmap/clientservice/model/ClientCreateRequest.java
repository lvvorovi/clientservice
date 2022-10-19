package com.roadmap.clientservice.model;

import com.roadmap.clientservice.business.exception.message.ExceptionMessage;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Validated
@NotNull
public class ClientCreateRequest {

    @Null
    private Long id;

    @NotBlank(message = ExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ExceptionMessage.FIELD_MAX_LENGTH_45)
    private String firstName;

    @NotBlank(message = ExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ExceptionMessage.FIELD_MAX_LENGTH_45)
    private String lastName;

    @NotNull(message = ExceptionMessage.FIELD_NOT_NULL)
    @PastOrPresent(message = ExceptionMessage.FIELD_PAST_OR_PRESENT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @NotBlank(message = ExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ExceptionMessage.FIELD_MAX_LENGTH_45)
    private String passportNumber;

    @NotBlank(message = ExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ExceptionMessage.FIELD_MAX_LENGTH_45)
    private String personalNumber;

    @NotBlank(message = ExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ExceptionMessage.FIELD_MAX_LENGTH_45)
    private String phoneNumber;

    @NotBlank(message = ExceptionMessage.FIELD_NOT_BLANK)
    @Size(max = 45, message = ExceptionMessage.FIELD_MAX_LENGTH_45)
    private String occupation;

    @NotNull(message = ExceptionMessage.FIELD_NOT_NULL)
    @Digits(integer = 25, fraction = 2, message = ExceptionMessage.FIELD_MAX_DIGITS_25_2)
    private BigDecimal expenses;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime created;

}
