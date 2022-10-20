package com.roadmap.clientservice.business.validation.service.impl;

import com.roadmap.clientservice.business.validation.rule.ClientCreateValidationRule;
import com.roadmap.clientservice.business.validation.rule.ClientUpdateValidationRule;
import com.roadmap.clientservice.business.validation.service.ClientValidationService;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientValidationServiceImpl implements ClientValidationService {

    private final List<ClientCreateValidationRule> createRuleList;
    private final List<ClientUpdateValidationRule> updateRuleList;

    @Override
    public void validate(ClientCreateRequest request) {
        createRuleList.forEach(rule -> rule.validate(request));
    }

    @Override
    public void validate(ClientUpdateRequest request) {
        updateRuleList.forEach(rule -> rule.validate(request));
    }
}
