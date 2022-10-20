package com.roadmap.clientservice.business.validation.rule.impl;

import com.roadmap.clientservice.business.repository.ClientJpaRepository;
import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.business.validation.exception.ClientExistsException;
import com.roadmap.clientservice.business.validation.rule.ClientCreateValidationRule;
import com.roadmap.clientservice.business.validation.rule.ClientUpdateValidationRule;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.roadmap.clientservice.business.LogMessageStore.CLIENT_PERSONAL_NUMBER_EXISTS;

@Component
@RequiredArgsConstructor
public class ClientAlreadyExistsValidationRule implements ClientCreateValidationRule, ClientUpdateValidationRule {

    private final ClientJpaRepository repository;

    @Override
    public void validate(ClientCreateRequest request) {
        Optional<ClientEntity> entity = repository.findByPersonalNumber(request.getPersonalNumber());
        if (entity.isPresent()) {
            throw new ClientExistsException(CLIENT_PERSONAL_NUMBER_EXISTS + request.getPersonalNumber());
        }
    }

    @Override
    public void validate(ClientUpdateRequest request) {
        Optional<ClientEntity> entity = repository.findByPersonalNumber(request.getPersonalNumber());
        if (entity.isEmpty()) return;
        boolean idDoNotMatch = !request.getId().equals(entity.get().getId());
        if (idDoNotMatch) {
            throw new ClientExistsException(CLIENT_PERSONAL_NUMBER_EXISTS + request.getPersonalNumber());
        }
    }
}
