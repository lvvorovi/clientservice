package com.roadmap.clientservice.business.mapper.impl;

import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import com.roadmap.clientservice.util.ClientTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
@ActiveProfiles("logging-test")
class ClientModelMapperTest {

    @Autowired
    private ClientModelMapper victim;

    @Test
    void entityToDto_whenReceiveEntity_thenReturnResponse(CapturedOutput output) {
        ClientEntity entity = ClientTestUtil.clientEntity();
        ClientResponse expected = ClientTestUtil.clientResponseDto(entity);

        ClientResponse result = victim.entityToDto(entity);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(entity.toString()));
        assertTrue(output.getOut().contains(expected.toString()));
    }

    @Test
    void dtoToEntity_whenCreateRequest_thenReturnEntity(CapturedOutput output) {
        ClientEntity expected = ClientTestUtil.clientEntity();
        expected.setId(null);
        ClientCreateRequest requestDto = ClientTestUtil.clientCreateRequestDto(expected);

        ClientEntity result = victim.dtoToEntity(requestDto);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(expected.toString()));
        assertTrue(output.getOut().contains(requestDto.toString()));
    }

    @Test
    void dtoToEntity_whenUpdateRequest_thenReturnEntity(CapturedOutput output) {
        ClientEntity expected = ClientTestUtil.clientEntity();
        ClientUpdateRequest requestDto = ClientTestUtil.clientUpdateRequestDto(expected);

        ClientEntity result = victim.dtoToEntity(requestDto);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(expected.toString()));
        assertTrue(output.getOut().contains(requestDto.toString()));
    }

}
