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

    private final ModelMapper modelMapper;
    private static final String LOG_MESSAGE = "{} mapped {} to {}";

    @Override
    public ClientResponse entityToDto(ClientEntity entity) {
        ClientResponse dto = modelMapper.map(entity, ClientResponse.class);
        log.debug(LOG_MESSAGE, this.getClass(), entity, dto);
        return dto;
    }

    @Override
    public ClientEntity dtoToEntity(ClientCreateRequest dto) {
        ClientEntity entity = modelMapper.map(dto, ClientEntity.class);
        log.debug(LOG_MESSAGE, this.getClass(), dto, entity);
        return entity;
    }

    @Override
    public ClientEntity dtoToEntity(ClientUpdateRequest dto) {
        ClientEntity entity = modelMapper.map(dto, ClientEntity.class);
        log.debug(LOG_MESSAGE, this.getClass(), dto, entity);
        return entity;
    }
}
