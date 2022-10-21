package com.roadmap.clientservice.web.controller;

import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static com.roadmap.clientservice.business.LogMessageStore.*;
import static com.roadmap.clientservice.util.ClientTestUtil.*;
import static com.roadmap.clientservice.util.JsonUtil.jsonToClientResponse;
import static com.roadmap.clientservice.util.JsonUtil.objectToJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ExtendWith(OutputCaptureExtension.class)
class ClientControllerIntegrationTest {

    @Autowired
    MockMvc mvc;

    @Test
    void save_whenValidBody_thenSave_andReturnResponse(CapturedOutput output) throws Exception {
        ClientEntity entity = clientEntity();
        ClientCreateRequest request = clientCreateRequest(entity);
        ClientResponse expected = clientResponse(entity);

        MvcResult mvcResult = mvc.perform(post(save_uri)
                        .contentType(APPLICATION_JSON)
                        .content(objectToJson(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        String locationHeader = mvcResult.getResponse().getHeader("location");
        ClientResponse result = jsonToClientResponse(content);
        assertNotEquals(expected, result);
        assertNotNull(result.getId());
        assertNotNull(locationHeader);
        assertTrue(locationHeader.endsWith(result.getId().toString()));
        assertTrue(output.getOut().contains(CLIENT_SAVE_REQUEST_LOG + request));

    }

    @Test
    void findById_whenFound_thenReturn(CapturedOutput output) throws Exception {

        MvcResult mvcResult = mvc.perform(get(findById_uri))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ClientResponse result = jsonToClientResponse(content);
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertTrue(output.getOut().contains(CLIENT_FOUND_LOG));
    }

    @Test
    void update_whenValidRequest_thenUpdate_andReturnResponse(CapturedOutput output) throws Exception {
        ClientEntity entity = clientEntity();
        ClientUpdateRequest request = clientUpdateRequest(entity);
        ClientResponse expected = clientResponse(entity);

        MvcResult mvcResult = mvc.perform(put(update_uri)
                        .contentType(APPLICATION_JSON)
                        .content(objectToJson(request)))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ClientResponse result = jsonToClientResponse(content);

        assertEquals(expected, result);
        assertTrue(output.getOut().contains(CLIENT_UPDATED_LOG));
    }

    @Test
    void delete_whenValidRequest_thenDelete_andReturnEmptyBody(CapturedOutput output) {

    }
}
