package com.roadmap.clientservice.business.service;

import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;

public interface ClientService {

    ClientResponse save(ClientCreateRequest dto);

    ClientResponse findById(Long id);

    ClientResponse update(ClientUpdateRequest dto);

    void deleteById(Long id);


}
