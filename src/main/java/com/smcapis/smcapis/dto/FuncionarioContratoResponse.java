package com.smcapis.smcapis.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Contrato del listado; no modifica la respuesta individual del funcionario. */
public record FuncionarioContratoResponse(Integer rut, String vrut, String paterno, String materno, String email,
        String codDeptoExt, String nombres, String foto, String departamento, Integer ident,
        String tipoContrato, String escalafon, int grado, LocalDate fechainicioContrato,
        LocalDate fechafinContrato, boolean vigente, LocalDate fechaNacimiento,
        @JsonProperty("numresolcontr") String numResolContr,
        @JsonProperty("obs_adicional_cont") String obsAdicionalCont,
        @JsonProperty("lincontrato") Integer linContrato,
        @JsonProperty("fecharesolcontr") LocalDate fechaResolContr,
        @JsonProperty("denominacion_dominio") String denominacionDominio) {
}
