package com.roadmap.clientservice.web.controller;

import com.roadmap.clientservice.business.repository.model.ClientEntity;
import com.roadmap.clientservice.business.service.ClientService;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.roadmap.clientservice.business.service.message.ClientMessage.*;
import static com.roadmap.clientservice.util.ClientTestUtil.*;
import static com.roadmap.clientservice.util.JsonUtil.jsonToClientResponse;
import static com.roadmap.clientservice.util.JsonUtil.objectToJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc
@ExtendWith(OutputCaptureExtension.class)
class ClientControllerUnitTest {

    @MockBean
    ClientService service;
    @Autowired
    MockMvc mvc;

    @Test
    void save_whenValidCreateRequest_thenSave_andReturnResponse(CapturedOutput output) throws Exception {
        ClientEntity entity = clientEntity();
        ClientCreateRequest request = clientCreateRequest(entity);
        ClientResponse expected = clientResponse(entity);
        request.setId(null);
        when(service.save(request)).thenReturn(expected);

        MvcResult mvcResult = mvc.perform(post(save_uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ClientResponse result = jsonToClientResponse(content);
        assertEquals(expected, result);
        assertTrue(output.getOut().contains(SAVE_REQUEST_LOG + request));
        verify(service, times(1)).save(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void save_whenNotValidRequestJson_thenDoNotSave_andThrowException(CapturedOutput output) throws Exception {
        ClientEntity entity = clientEntity();
        ClientCreateRequest request = clientCreateRequest(entity);
        request.setFirstName(null);

        mvc.perform(post(save_uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(request)))
                .andExpect(status().isBadRequest());

        assertFalse(output.getOut().contains(SAVE_REQUEST_LOG + request));
        verifyNoInteractions(service);
    }

    @Test
    void findById_whenFound_thenReturnResponse(CapturedOutput output) throws Exception {
        ClientEntity entity = clientEntity();
        ClientResponse expected = clientResponse(entity);
        when(service.findById(id)).thenReturn(expected);

        MvcResult mvcResult = mvc.perform(get(findById_uri))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ClientResponse result = jsonToClientResponse(content);
        assertEquals(expected, result);
        assertTrue(output.getOut().contains(FIND_BY_ID_REQUEST_LOG + id));
        verify(service, times(1)).findById(id);
        verifyNoMoreInteractions(service);
    }

    @Test
    void update_whenValidBody_thenPassToService_andReturnResponse(CapturedOutput output) throws Exception {
        ClientEntity entity = clientEntity();
        ClientUpdateRequest request = clientUpdateRequest(entity);
        ClientResponse expected = clientResponse(entity);
        when(service.update(request)).thenReturn(expected);

        MvcResult mvcResult = mvc.perform(put(update_uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(request)))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ClientResponse result = jsonToClientResponse(content);
        assertEquals(expected, result);
        assertTrue(output.getOut().contains(UPDATE_REQUEST_LOG + request));
        verify(service, times(1)).update(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void update_whenNotValidBody_thenDoNotUpdate_andThrowException(CapturedOutput output) throws Exception {
        ClientEntity entity = clientEntity();
        ClientUpdateRequest request = clientUpdateRequest(entity);
        request.setFirstName(null);

        mvc.perform(put(update_uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(request)))
                .andExpect(status().isBadRequest());

        assertFalse(output.getOut().contains(UPDATE_REQUEST_LOG + request));
        verifyNoInteractions(service);
    }

    @Test
    void deleteById_whenId_thenPassToService_andReturnNoContent(CapturedOutput output) throws Exception {
        doNothing().when(service).deleteById(id);
        mvc.perform(delete(deleteById_uri))
                .andExpect(status().isNoContent());

        assertTrue(output.getOut().contains(DELETE_BY_ID_REQUEST_LOG + id));
        verify(service, times(1)).deleteById(id);
        verifyNoMoreInteractions(service);
    }


}