package com.roadmap.clientservice.business.service.impl;

import com.roadmap.clientservice.business.mapper.ClientMapper;
import com.roadmap.clientservice.business.repository.ClientJpaRepository;
import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.business.service.ClientService;
import com.roadmap.clientservice.business.validation.exception.ClientNotFoundException;
import com.roadmap.clientservice.business.validation.service.ClientValidationService;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.roadmap.clientservice.business.validation.exception.message.ValidationExceptionMessage.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientJpaRepository repository;
    private final ClientMapper mapper;
    private final ClientValidationService validationService;

    @Override
    public ClientResponse save(ClientCreateRequest request) {
        validationService.validate(request);
        ClientEntity entityToSave = mapper.requestToEntity(request);
        ClientEntity savedEntity = repository.save(entityToSave);
        log.info(CLIENT_SAVED + savedEntity);
        return mapper.entityToResponse(savedEntity);
    }

    @Override
    public ClientResponse findById(Long id) {
        ClientEntity entity = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(CLIENT_ID_NOT_FOUND + id));
        log.debug(CLIENT_FOUND + entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    public ClientResponse update(ClientUpdateRequest request) {
        validationService.validate(request);
        ClientEntity requestEntity = mapper.requestToEntity(request);
        ClientEntity updatedEntity = repository.save(requestEntity);
        log.info(CLIENT_UPDATED + updatedEntity);
        return mapper.entityToResponse(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            log.info(CLIENT_DELETED_BY_ID + id);
        } else {
            throw new ClientNotFoundException(CLIENT_ID_NOT_FOUND + id);
        }
    }
}
