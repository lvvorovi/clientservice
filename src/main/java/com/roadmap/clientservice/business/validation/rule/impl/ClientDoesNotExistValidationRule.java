package com.roadmap.clientservice.business.validation.rule.impl;

import com.roadmap.clientservice.business.validation.exception.ClientNotFoundException;
import com.roadmap.clientservice.business.validation.rule.ClientValidationRule;
import com.roadmap.clientservice.business.repository.ClientJpaRepository;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.roadmap.clientservice.business.validation.exception.message.ValidationExceptionMessage.CLIENT_ID_NOT_FOUND;


@Component
@RequiredArgsConstructor
public class ClientDoesNotExistValidationRule implements ClientValidationRule {

    private final ClientJpaRepository repository;
    @Override
    public void validate(ClientCreateRequest request) {
    }

    @Override
    public void validate(ClientUpdateRequest request) {
        boolean exists = repository.existsById(request.getId());
        if (!exists) {
            throw new ClientNotFoundException(CLIENT_ID_NOT_FOUND + request.getId());
        }
    }
}
