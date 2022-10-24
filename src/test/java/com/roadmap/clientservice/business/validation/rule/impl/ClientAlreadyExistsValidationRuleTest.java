package com.roadmap.clientservice.business.validation.rule.impl;

import com.roadmap.clientservice.business.repository.ClientJpaRepository;
import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.business.validation.exception.ClientExistsException;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.roadmap.clientservice.business.LogMessageStore.CLIENT_PERSONAL_NUMBER_EXISTS;
import static com.roadmap.clientservice.util.ClientTestUtil.*;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientAlreadyExistsValidationRuleTest {

    @Mock
    ClientJpaRepository repository;
    @InjectMocks
    ClientAlreadyExistsValidationRule victim;

    @Test
    void validate_createRequest_whenDoesNotExist_thenNoException() {
        ClientCreateRequest request = clientCreateRequest(clientEntity());
        when(repository.existsByPersonalNumber(request.getPersonalNumber())).thenReturn(false);

        assertThatNoException().isThrownBy(() -> victim.validate(request));

        verify(repository, times(1)).existsByPersonalNumber(request.getPersonalNumber());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void validate_createRequest_whenExists_thenThrowClientExistsException() {
        ClientEntity entity = clientEntity();
        ClientCreateRequest request = clientCreateRequest(entity);
        when(repository.existsByPersonalNumber(request.getPersonalNumber())).thenReturn(true);

        assertThatThrownBy(() -> victim.validate(request))
                .isInstanceOf(ClientExistsException.class)
                .hasMessage(CLIENT_PERSONAL_NUMBER_EXISTS + request.getPersonalNumber());

        verify(repository, times(1)).existsByPersonalNumber(request.getPersonalNumber());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void validate_updateRequest_whenNotFound_thenNoException() {
        ClientUpdateRequest request = clientUpdateRequest(clientEntity());
        when(repository.findByPersonalNumber(request.getPersonalNumber())).thenReturn(Optional.empty());

        assertThatNoException().isThrownBy(() -> victim.validate(request));

        verify(repository, times(1)).findByPersonalNumber(request.getPersonalNumber());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void validate_updateRequest_whenFound_andIdMatch_thenNoException() {
        ClientEntity entity = clientEntity();
        ClientUpdateRequest request = clientUpdateRequest(entity);
        when(repository.findByPersonalNumber(request.getPersonalNumber())).thenReturn(Optional.of(entity));

        assertThatNoException().isThrownBy(() -> victim.validate(request));

        verify(repository, times(1)).findByPersonalNumber(request.getPersonalNumber());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void validate_updateRequest_whenFound_andIdNotMatch_thenThrowClientExistsException() {
        ClientEntity entity = clientEntity();
        ClientUpdateRequest request = clientUpdateRequest(entity);
        request.setId(request.getId() + 1);
        when(repository.findByPersonalNumber(request.getPersonalNumber())).thenReturn(Optional.of(entity));

        assertThatThrownBy(() -> victim.validate(request))
                .isInstanceOf(ClientExistsException.class)
                .hasMessage(CLIENT_PERSONAL_NUMBER_EXISTS + request.getPersonalNumber());

        verify(repository, times(1)).findByPersonalNumber(request.getPersonalNumber());
        verifyNoMoreInteractions(repository);
    }

}