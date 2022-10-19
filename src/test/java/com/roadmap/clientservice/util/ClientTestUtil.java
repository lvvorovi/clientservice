package com.roadmap.clientservice.util;

import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ClientTestUtil {

    public static ClientEntity clientEntity() {
        ClientEntity entity = new ClientEntity();
        entity.setId(1L);
        entity.setFirstName("FirstName");
        entity.setLastName("LastName");
        entity.setCreated(LocalDateTime.now());
        entity.setExpenses(new BigDecimal(10));
        entity.setOccupation("occupation");
        entity.setDateOfBirth(LocalDate.now().minusDays(10));
        entity.setPassportNumber(UUID.randomUUID().toString());
        entity.setPhoneNumber("+12312345678");
        entity.setPersonalNumber(UUID.randomUUID().toString());
        return entity;
    }

    public static ClientCreateRequest clientCreateRequestDto(ClientEntity entity) {
        ClientCreateRequest request = new ClientCreateRequest();
        request.setDateOfBirth(entity.getDateOfBirth());
        request.setFirstName(entity.getFirstName());
        request.setLastName(entity.getLastName());
        request.setId(null);
        request.setCreated(entity.getCreated());
        request.setExpenses(entity.getExpenses());
        request.setOccupation(entity.getOccupation());
        request.setPassportNumber(entity.getPassportNumber());
        request.setPhoneNumber(entity.getPhoneNumber());
        request.setPersonalNumber(entity.getPersonalNumber());
        return request;
    }

    public static ClientUpdateRequest clientUpdateRequestDto(ClientEntity entity) {
        ClientUpdateRequest request = new ClientUpdateRequest();
        request.setDateOfBirth(entity.getDateOfBirth());
        request.setFirstName(entity.getFirstName());
        request.setLastName(entity.getLastName());
        request.setId(entity.getId());
        request.setCreated(entity.getCreated());
        request.setExpenses(entity.getExpenses());
        request.setOccupation(entity.getOccupation());
        request.setPassportNumber(entity.getPassportNumber());
        request.setPhoneNumber(entity.getPhoneNumber());
        request.setPersonalNumber(entity.getPersonalNumber());
        return request;
    }

    public static ClientResponse clientResponseDto(ClientEntity entity) {
        ClientResponse response = new ClientResponse();
        response.setDateOfBirth(entity.getDateOfBirth());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setId(entity.getId());
        response.setCreated(entity.getCreated());
        response.setExpenses(entity.getExpenses());
        response.setOccupation(entity.getOccupation());
        response.setPassportNumber(entity.getPassportNumber());
        response.setPhoneNumber(entity.getPhoneNumber());
        response.setPersonalNumber(entity.getPersonalNumber());
        return response;
    }

}
