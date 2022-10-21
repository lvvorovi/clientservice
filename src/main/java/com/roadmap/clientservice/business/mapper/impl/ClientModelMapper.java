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

import static com.roadmap.clientservice.business.LogMessageStore.MAPPER_LOG_MESSAGE;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientModelMapper implements ClientMapper {

    private final ModelMapper modelMapper;

    @Override
    public ClientResponse entityToResponse(ClientEntity entity) {
        ClientResponse response = modelMapper.map(entity, ClientResponse.class);
        if (log.isDebugEnabled()) {
            log.debug(entity + MAPPER_LOG_MESSAGE + response);
        }
        return response;
    }

    @Override
    public ClientEntity requestToEntity(ClientCreateRequest request) {
        ClientEntity entity = modelMapper.map(request, ClientEntity.class);
        if (log.isDebugEnabled()) {
            log.debug(request + MAPPER_LOG_MESSAGE + entity);
        }
        return entity;
    }

    @Override
    public ClientEntity requestToEntity(ClientUpdateRequest request) {
        ClientEntity entity = modelMapper.map(request, ClientEntity.class);
        if (log.isDebugEnabled()) {
            log.debug(request + MAPPER_LOG_MESSAGE + entity);
        }
        return entity;
    }
}
