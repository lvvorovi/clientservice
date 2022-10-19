package com.roadmap.clientservice.business.service.impl;

import com.roadmap.clientservice.business.exception.ClientNotFoundException;
import com.roadmap.clientservice.business.exception.message.ExceptionMessage;
import com.roadmap.clientservice.business.mapper.ClientMapper;
import com.roadmap.clientservice.business.repository.ClientJpaRepository;
import com.roadmap.clientservice.business.repository.model.ClientEntity;
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

import static com.roadmap.clientservice.util.ClientTestUtil.*;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class ClientServiceImplTest {

    @Mock
    ClientJpaRepository repository;
    @Mock
    ClientMapper mapper;
    @InjectMocks
    ClientServiceImpl victim;


    @Test
    void save_whenRequestDto_thenSaveEntity_returnDto(CapturedOutput output) {
        ClientEntity requestEntity = clientEntity();
        requestEntity.setId(null);
        ClientCreateRequest requestDto = clientCreateRequestDto(requestEntity);
        ClientEntity savedEntity = clientEntity();
        ClientResponse expected = clientResponseDto(savedEntity);
        when(mapper.dtoToEntity(requestDto)).thenReturn(requestEntity);
        when(repository.save(requestEntity)).thenReturn(savedEntity);
        when(mapper.entityToDto(savedEntity)).thenReturn(expected);

        ClientResponse result = victim.save(requestDto);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(savedEntity.toString()));
        verify(mapper, times(1)).dtoToEntity(requestDto);
        verify(repository, times(1)).save(requestEntity);
        verify(mapper, times(1)).entityToDto(savedEntity);
        verifyNoMoreInteractions(mapper, repository);
    }

    @Test
    void findById_whenFound_thenReturnResponseDto(CapturedOutput output) {
        ClientEntity entity = clientEntity();
        ClientResponse expected = clientResponseDto(entity);
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(mapper.entityToDto(entity)).thenReturn(expected);

        ClientResponse result = victim.findById(entity.getId());

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(result.toString()));
        verify(repository, times(1)).findById(entity.getId());
        verify(mapper, times(1)).entityToDto(entity);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void findById_whenNotFound_thenThrowClientNotFoundException(CapturedOutput output) {
        ClientEntity entity = clientEntity();
        Long id = entity.getId();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findById(id))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage(ExceptionMessage.CLIENT_WITH_ID_NOT_FOUND + entity.getId());

        assertFalse(output.getOut().contains(entity.toString()));
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }


    @Test
    void update_whenRequestDto_andNotFound_thenThrowClientNotFoundException(CapturedOutput output) {
        ClientEntity requestEntity = clientEntity();
        ClientUpdateRequest requestDto = clientUpdateRequestDto(requestEntity);
        when(repository.findById(requestDto.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.update(requestDto))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage(ExceptionMessage.CLIENT_WITH_ID_NOT_FOUND + requestEntity.getId());

        assertFalse(output.getOut().contains(requestEntity.toString()));
        verify(repository, times(1)).findById(requestDto.getId());
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void update_whenRequestDto_andFound_thenUpdateEntity_andReturnDto(CapturedOutput output) {
        ClientEntity requestEntity = clientEntity();
        ClientUpdateRequest requestDto = clientUpdateRequestDto(requestEntity);
        ClientEntity updatedEntity = clientEntity();
        updatedEntity.setFirstName("New First Name");
        ClientResponse expected = clientResponseDto(updatedEntity);

        when(repository.findById(requestDto.getId())).thenReturn(Optional.of(requestEntity));
        when(mapper.dtoToEntity(requestDto)).thenReturn(requestEntity);
        when(repository.save(requestEntity)).thenReturn(updatedEntity);
        when(mapper.entityToDto(updatedEntity)).thenReturn(expected);

        ClientResponse result = victim.update(requestDto);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(requestEntity.toString()));
        assertTrue(output.getOut().contains(updatedEntity.toString()));
        verify(repository, times(1)).findById(requestDto.getId());
        verify(mapper, times(1)).dtoToEntity(requestDto);
        verify(repository, times(1)).save(requestEntity);
        verify(mapper, times(1)).entityToDto(updatedEntity);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void deleteById_whenId_andFound_thenDelete(CapturedOutput output) {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);

        assertThatNoException().isThrownBy(() -> victim.deleteById(id));

        assertTrue(output.getOut().contains("Client deleted"));
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void deleteById_whenId_andNotFound_thenThrowClientNotFoundException(CapturedOutput output) {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        assertThatThrownBy(() -> victim.deleteById(id))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage(ExceptionMessage.CLIENT_WITH_ID_NOT_FOUND + id);

        assertFalse(output.getOut().contains("Client deleted"));
        verify(repository, times(1)).existsById(id);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }



}
