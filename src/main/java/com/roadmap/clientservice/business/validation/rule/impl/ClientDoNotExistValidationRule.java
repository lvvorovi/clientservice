package com.roadmap.clientservice.business.validation.rule.impl;

import com.roadmap.clientservice.business.repository.ClientJpaRepository;
import com.roadmap.clientservice.business.validation.exception.ClientNotFoundException;
import com.roadmap.clientservice.business.validation.rule.ClientUpdateValidationRule;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.roadmap.clientservice.business.LogMessageStore.CLIENT_ID_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class ClientDoNotExistValidationRule implements ClientUpdateValidationRule {

    private final ClientJpaRepository repository;

    @Override
    public void validate(ClientUpdateRequest request) {
        boolean doNotExist = !repository.existsById(request.getId());
        if (doNotExist) {
            throw new ClientNotFoundException(CLIENT_ID_NOT_FOUND + request.getId());
        }
    }
}
