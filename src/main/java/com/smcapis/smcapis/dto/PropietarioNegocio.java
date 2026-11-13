package com.smcapis.smcapis.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PropietarioNegocio {

    private Integer rut;
    private String vrut;
    private String nombre;
    private String direccion;
    private String fono;
    private String email;
    private List<DetalleNegocio> negocios;

}
