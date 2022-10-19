package com.roadmap.clientservice.business.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "personal_number")
    private String personalNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "monthly_expenses")
    private BigDecimal expenses;

    @Column(name = "date_created")
    private LocalDateTime created;

}
