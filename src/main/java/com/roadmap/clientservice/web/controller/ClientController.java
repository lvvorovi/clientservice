package com.roadmap.clientservice.web.controller;

import com.roadmap.clientservice.business.service.ClientService;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import com.roadmap.clientservice.model.ClientUpdateRequest;
import com.roadmap.clientservice.model.ErrorDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.roadmap.clientservice.business.LogMessageStore.*;
import static com.roadmap.clientservice.swagger.ApiResponseStore.*;
import static com.roadmap.clientservice.swagger.SwagerTagStore.CLIENT_CONTROLLER_TAG;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/v1/clients")
@RequiredArgsConstructor
@Slf4j
@Api(tags = CLIENT_CONTROLLER_TAG)
@ApiResponses({
        @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE, response = ErrorDto.class),
        @ApiResponse(code = UNAUTHORIZED_CODE, message = UNAUTHORIZED_MESSAGE, response = ErrorDto.class),
        @ApiResponse(code = UNAUTHENTICATED_CODE, message = UNAUTHENTICATED_MESSAGE, response = ErrorDto.class),
        @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE, response = ErrorDto.class),
        @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE, response = ErrorDto.class)})
public class ClientController {

    private final ClientService service;

    @ApiResponse(code = CREATED_CODE, message = CREATED_MESSAGE)
    @ResponseHeader(name = "location")
    @ResponseStatus(CREATED)
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
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

    @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    @ResponseStatus(OK)
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        log.info(CLIENT_FIND_BY_ID_REQUEST_LOG + id);
        ClientResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    @ResponseStatus(OK)
    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponse> update(@Valid @RequestBody ClientUpdateRequest request) {
        log.info(CLIENT_UPDATE_REQUEST_LOG + request);
        ClientResponse response = service.update(request);
        return ResponseEntity.ok(response);
    }

    @ApiResponse(code = NO_CONTENT_CODE, message = NO_CONTENT_MESSAGE)
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info(CLIENT_DELETE_BY_ID_REQUEST_LOG + id);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
