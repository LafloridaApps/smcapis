package com.smcapis.smcapis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ItemLiquidacion(
    @JsonProperty("liquidacion_id") int liquidacionId,
    String tipo,
    String codigo,
    String nombre,
    String detalle,
    int valor
) {
}
