package com.roadmap.clientservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.roadmap.clientservice.business.LogMessageStore.FIELD_NOT_NULL;

@Data
@EqualsAndHashCode(callSuper = true)
@Validated
@NotNull
public class ClientUpdateRequest extends ClientCreateRequest {

    @NotNull(message = FIELD_NOT_NULL)
    @NumberFormat
    @Valid
    private Long id;

}
