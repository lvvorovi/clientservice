package com.roadmap.clientservice.business.validation.rule;

import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientUpdateRequest;

public interface ClientValidationRule {

    void validate(ClientCreateRequest request);
    void validate(ClientUpdateRequest request);

}
