package com.roadmap.clientservice.business.validation.rule.impl;

import com.roadmap.clientservice.business.repository.ClientJpaRepository;
import com.roadmap.clientservice.business.validation.exception.ClientNotFoundException;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.roadmap.clientservice.business.LogMessageStore.CLIENT_ID_NOT_FOUND;
import static com.roadmap.clientservice.util.ClientTestUtil.clientEntity;
import static com.roadmap.clientservice.util.ClientTestUtil.clientUpdateRequest;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientDoNotExistValidationRuleTest {

    @Mock
    ClientJpaRepository repository;
    @InjectMocks
    ClientDoNotExistValidationRule victim;

    @Test
    void validate_updateRequest_whenNotFound_thenThrowClientNotFoundException() {
        ClientUpdateRequest request = clientUpdateRequest(clientEntity());
        when(repository.existsById(request.getId())).thenReturn(false);

        assertThatThrownBy(() -> victim.validate(request))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage(CLIENT_ID_NOT_FOUND + request.getId());

        verify(repository, times(1)).existsById(request.getId());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void validate_updateRequest_whenFound_thenNoException() {
        ClientUpdateRequest request = clientUpdateRequest(clientEntity());
        when(repository.existsById(request.getId())).thenReturn(true);

        assertThatNoException().isThrownBy(() -> victim.validate(request));

        verify(repository, times(1)).existsById(request.getId());
        verifyNoMoreInteractions(repository);
    }
}