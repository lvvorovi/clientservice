package com.roadmap.clientservice.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.roadmap.clientservice.business.LogMessageStore.*;

@Data
@Validated
@NotNull
public class ClientCreateRequest {

    @NotBlank(message = FIELD_NOT_BLANK)
    @Size(max = 45, message = FIELD_MAX_LENGTH_45)
    private String firstName;

    @NotBlank(message = FIELD_NOT_BLANK)
    @Size(max = 45, message = FIELD_MAX_LENGTH_45)
    private String lastName;

    @NotNull(message = FIELD_NOT_NULL)
    @PastOrPresent(message = FIELD_PAST_OR_PRESENT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @NotBlank(message = FIELD_NOT_BLANK)
    @Size(max = 45, message = FIELD_MAX_LENGTH_45)
    private String passportNumber;

    @NotBlank(message = FIELD_NOT_BLANK)
    @Size(max = 45, message = FIELD_MAX_LENGTH_45)
    private String personalNumber;

    @NotBlank(message = FIELD_NOT_BLANK)
    @Size(max = 45, message = FIELD_MAX_LENGTH_45)
    private String phoneNumber;

    @NotBlank(message = FIELD_NOT_BLANK)
    @Size(max = 45, message = FIELD_MAX_LENGTH_45)
    private String occupation;

    @NotNull(message = FIELD_NOT_NULL)
    @Digits(integer = 25, fraction = 2, message = FIELD_MAX_DIGITS_25_2)
    private BigDecimal expenses;

    @PastOrPresent(message = FIELD_PAST_OR_PRESENT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime created;

}
