package com.roadmap.clientservice.web.controller;

import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static com.roadmap.clientservice.business.service.message.ClientMessage.SAVE_REQUEST_LOG;
import static com.roadmap.clientservice.util.ClientTestUtil.*;
import static com.roadmap.clientservice.util.JsonUtil.jsonToClientResponse;
import static com.roadmap.clientservice.util.JsonUtil.objectToJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        request.setId(null);
        ClientResponse expected = clientResponse(entity);

        MvcResult mvcResult = mvc.perform(post(save_uri)
                        .contentType(MediaType.APPLICATION_JSON)
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
        assertTrue(output.getOut().contains(SAVE_REQUEST_LOG + request));

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
    }

}
