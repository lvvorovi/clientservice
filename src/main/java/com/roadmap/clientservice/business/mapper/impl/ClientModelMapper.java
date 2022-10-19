package com.roadmap.clientservice.business.mapper.impl;

import com.roadmap.clientservice.business.mapper.ClientMapper;
import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientModelMapper implements ClientMapper {

    private static final String LOG_MESSAGE = "{} mapped {} to {}";
    private final ModelMapper modelMapper;

    @Override
    public ClientResponse entityToResponse(ClientEntity entity) {
        ClientResponse dto = modelMapper.map(entity, ClientResponse.class);
        log.debug(LOG_MESSAGE, this.getClass(), entity, dto);
        return dto;
    }

    @Override
    public ClientEntity requestToEntity(ClientCreateRequest request) {
        ClientEntity entity = modelMapper.map(request, ClientEntity.class);
        log.debug(LOG_MESSAGE, this.getClass(), request, entity);
        return entity;
    }

    @Override
    public ClientEntity requestToEntity(ClientUpdateRequest request) {
        ClientEntity entity = modelMapper.map(request, ClientEntity.class);
        log.debug(LOG_MESSAGE, this.getClass(), request, entity);
        return entity;
    }
}
