package com.roadmap.clientservice.business.validation.service;

import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientUpdateRequest;

public interface ClientValidationService {

    void validate(ClientCreateRequest request);

    void validate(ClientUpdateRequest request);

}
