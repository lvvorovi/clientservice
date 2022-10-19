package com.roadmap.clientservice.business.mapper;

import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;

public interface ClientMapper {

    ClientResponse entityToDto(ClientEntity entity);

    ClientEntity dtoToEntity(ClientCreateRequest dto);

    ClientEntity dtoToEntity(ClientUpdateRequest dto);
}
