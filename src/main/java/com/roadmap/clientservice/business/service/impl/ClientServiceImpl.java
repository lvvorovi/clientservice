package com.roadmap.clientservice.business.service.impl;

import com.roadmap.clientservice.business.mapper.ClientMapper;
import com.roadmap.clientservice.business.repository.ClientJpaRepository;
import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.business.exception.ClientNotFoundException;
import com.roadmap.clientservice.business.exception.message.ExceptionMessage;
import com.roadmap.clientservice.business.service.ClientService;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientJpaRepository repository;
    private final ClientMapper mapper;

    @Override
    public ClientResponse save(ClientCreateRequest requestDto) {
        ClientEntity entityToSave = mapper.dtoToEntity(requestDto);
        ClientEntity savedEntity = repository.save(entityToSave);
        log.info("Client saved: {}", savedEntity);
        return mapper.entityToDto(savedEntity);
    }

    @Override
    public ClientResponse findById(Long id) {
        ClientResponse dto = repository.findById(id)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new ClientNotFoundException(ExceptionMessage.CLIENT_WITH_ID_NOT_FOUND + id));
        log.debug("Client with id {} found: {}", id, dto);
        return dto;
    }

    @Override
    public ClientResponse update(ClientUpdateRequest requestDto) {
        ClientEntity entityToUpdate = repository.findById(requestDto.getId())
                .orElseThrow(() -> new ClientNotFoundException(
                        ExceptionMessage.CLIENT_WITH_ID_NOT_FOUND + requestDto.getId()));

        ClientEntity requestEntity = mapper.dtoToEntity(requestDto);
        ClientEntity updatedEntity = repository.save(requestEntity);
        log.info("Client was updated from {} to {}", entityToUpdate, updatedEntity);
        return mapper.entityToDto(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            log.info("Client deleted with id: {}", id);
        } else {
            throw new ClientNotFoundException(ExceptionMessage.CLIENT_WITH_ID_NOT_FOUND + id);
        }
    }
}
