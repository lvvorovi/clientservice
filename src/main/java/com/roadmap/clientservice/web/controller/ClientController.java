package com.roadmap.clientservice.web.controller;

import com.roadmap.clientservice.business.service.ClientService;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.roadmap.clientservice.business.LogMessageStore.*;

@RestController
@RequestMapping("api/v1/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService service;

    @PostMapping(produces = "application/json")
    public ResponseEntity<ClientResponse> save(@Valid @RequestBody ClientCreateRequest request,
                                               UriComponentsBuilder builder) {
        log.info(CLIENT_SAVE_REQUEST_LOG + request);
        ClientResponse response = service.save(request);
        return ResponseEntity.created(
                        builder.path("api/v1/clients/{id}")
                                .buildAndExpand(response.getId())
                                .toUri())
                .body(response);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        log.info(CLIENT_FIND_BY_ID_REQUEST_LOG + id);
        ClientResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<ClientResponse> update(@Valid @RequestBody ClientUpdateRequest request) {
        log.info(CLIENT_UPDATE_REQUEST_LOG + request);
        ClientResponse response = service.update(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientResponse> deleteById(@PathVariable Long id) {
        log.info(CLIENT_DELETE_BY_ID_REQUEST_LOG + id);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
