package com.roadmap.clientservice.business.service.impl;

import com.roadmap.clientservice.business.mapper.ClientMapper;
import com.roadmap.clientservice.business.repository.ClientJpaRepository;
import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.business.validation.exception.ClientNotFoundException;
import com.roadmap.clientservice.business.validation.service.ClientValidationService;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.Optional;

import static com.roadmap.clientservice.business.LogMessageStore.*;
import static com.roadmap.clientservice.util.ClientTestUtil.*;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class ClientServiceImplUnitTest {

    @Mock
    ClientJpaRepository repository;
    @Mock
    ClientMapper mapper;
    @Mock
    ClientValidationService validationService;
    @InjectMocks
    ClientServiceImpl victim;

    @Test
    void save_whenRequest_thenValidate_saveEntity_returnResponse(CapturedOutput output) {
        ClientEntity requestEntity = clientEntity();
        requestEntity.setId(null);
        ClientCreateRequest request = clientCreateRequest(requestEntity);
        ClientEntity savedEntity = clientEntity();
        ClientResponse expected = clientResponse(savedEntity);
        doNothing().when(validationService).validate(request);
        when(mapper.requestToEntity(request)).thenReturn(requestEntity);
        when(repository.save(requestEntity)).thenReturn(savedEntity);
        when(mapper.entityToResponse(savedEntity)).thenReturn(expected);

        ClientResponse result = victim.save(request);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(CLIENT_SAVED_LOG + savedEntity));
        verify(mapper, times(1)).requestToEntity(request);
        verify(repository, times(1)).save(requestEntity);
        verify(mapper, times(1)).entityToResponse(savedEntity);
        verify(validationService, times(1)).validate(request);
        verifyNoMoreInteractions(mapper, repository, validationService);
    }

    @Test
    void findById_whenFound_thenReturnResponse(CapturedOutput output) {
        ClientEntity entity = clientEntity();
        ClientResponse expected = clientResponse(entity);
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(mapper.entityToResponse(entity)).thenReturn(expected);

        ClientResponse result = victim.findById(entity.getId());

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(CLIENT_FOUND_LOG + entity));
        verify(repository, times(1)).findById(entity.getId());
        verify(mapper, times(1)).entityToResponse(entity);
        verifyNoMoreInteractions(repository, mapper);
        verifyNoInteractions(validationService);
    }

    @Test
    void findById_whenNotFound_thenThrowClientNotFoundException(CapturedOutput output) {
        ClientEntity entity = clientEntity();
        Long id = entity.getId();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findById(id))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage(CLIENT_ID_NOT_FOUND + entity.getId());

        assertFalse(output.getOut().contains(CLIENT_FOUND_LOG));
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper, validationService);
    }

    @Test
    void update_whenRequest_thenValidate_updateEntity_andReturn(CapturedOutput output) {
        ClientEntity requestEntity = clientEntity();
        ClientUpdateRequest request = clientUpdateRequest(requestEntity);
        ClientEntity updatedEntity = clientEntity();
        updatedEntity.setFirstName("New First Name");
        ClientResponse expected = clientResponse(updatedEntity);
        when(mapper.requestToEntity(request)).thenReturn(requestEntity);
        when(repository.save(requestEntity)).thenReturn(updatedEntity);
        when(mapper.entityToResponse(updatedEntity)).thenReturn(expected);
        doNothing().when(validationService).validate(request);

        ClientResponse result = victim.update(request);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(CLIENT_UPDATED_LOG + updatedEntity));
        verify(mapper, times(1)).requestToEntity(request);
        verify(repository, times(1)).save(requestEntity);
        verify(mapper, times(1)).entityToResponse(updatedEntity);
        verify(validationService, times(1)).validate(request);
        verifyNoMoreInteractions(repository, mapper, validationService);
    }

    @Test
    void deleteById_whenId_andFound_thenDelete(CapturedOutput output) {
        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);

        assertThatNoException().isThrownBy(() -> victim.deleteById(id));

        assertTrue(output.getOut().contains(CLIENT_DELETED_BY_ID_LOG + id));
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper, validationService);
    }

    @Test
    void deleteById_whenId_andNotFound_thenThrowClientNotFoundException(CapturedOutput output) {
        when(repository.existsById(id)).thenReturn(false);

        assertThatThrownBy(() -> victim.deleteById(id))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage(CLIENT_ID_NOT_FOUND + id);

        assertFalse(output.getOut().contains(CLIENT_DELETED_BY_ID_LOG + id));
        verify(repository, times(1)).existsById(id);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper, validationService);
    }


}
