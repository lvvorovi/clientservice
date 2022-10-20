package com.roadmap.clientservice.business.validation.rule;

import com.roadmap.clientservice.model.ClientUpdateRequest;

public interface ClientUpdateValidationRule {

    void validate(ClientUpdateRequest request);

}
