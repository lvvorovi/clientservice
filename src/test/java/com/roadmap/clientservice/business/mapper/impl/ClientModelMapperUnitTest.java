package com.roadmap.clientservice.business.mapper.impl;

import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.ActiveProfiles;

import static com.roadmap.clientservice.business.LogMessageStore.MAPPER_LOG_MESSAGE;
import static com.roadmap.clientservice.util.ClientTestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
@ActiveProfiles("logging-test")
class ClientModelMapperUnitTest {

    @Autowired
    private ClientModelMapper victim;

    @Test
    void entityToDto_whenReceiveEntity_thenReturnResponse(CapturedOutput output) {
        ClientEntity entity = clientEntity();
        ClientResponse expected = clientResponse(entity);

        ClientResponse result = victim.entityToResponse(entity);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(entity + MAPPER_LOG_MESSAGE + expected));
    }

    @Test
    void dtoToEntity_whenCreateRequest_thenReturnEntity(CapturedOutput output) {
        ClientEntity expected = clientEntity();
        expected.setId(null);
        ClientCreateRequest request = clientCreateRequest(expected);

        ClientEntity result = victim.requestToEntity(request);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(request + MAPPER_LOG_MESSAGE + expected));
    }

    @Test
    void dtoToEntity_whenUpdateRequest_thenReturnEntity(CapturedOutput output) {
        ClientEntity expected = clientEntity();
        ClientUpdateRequest request = clientUpdateRequest(expected);

        ClientEntity result = victim.requestToEntity(request);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(request + MAPPER_LOG_MESSAGE + expected));
    }

}
