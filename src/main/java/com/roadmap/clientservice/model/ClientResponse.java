package com.roadmap.clientservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ClientResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String passportNumber;

    private String personalNumber;

    private String phoneNumber;

    private String occupation;

    private BigDecimal expenses;

    private LocalDateTime created;

}
