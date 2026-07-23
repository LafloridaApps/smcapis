package com.smcapis.smcapis.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AusenciasLicenciasFuncioanarioDto {

    private LocalDate fechaInicio;
    private LocalDate fechaTermino;
    private int ident;
    private Integer rut;

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    public int getIdent() {
        return ident;
    }

    public Integer getRut() {
        return rut;
    }

}
