package com.roadmap.clientservice.business.validation.rule;

import com.roadmap.clientservice.model.ClientCreateRequest;

public interface ClientCreateValidationRule {

    void validate(ClientCreateRequest request);

}
