package com.roadmap.clientservice.business.validation.service.impl;

import com.roadmap.clientservice.business.validation.rule.ClientValidationRule;
import com.roadmap.clientservice.business.validation.service.ClientValidationService;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientValidationServiceImpl implements ClientValidationService {

    private final List<ClientValidationRule> ruleList;

    @Override
    public void validate(ClientCreateRequest request) {
        ruleList.forEach(rule -> rule.validate(request));
    }

    @Override
    public void validate(ClientUpdateRequest request) {
        ruleList.forEach(rule -> rule.validate(request));
    }
}
