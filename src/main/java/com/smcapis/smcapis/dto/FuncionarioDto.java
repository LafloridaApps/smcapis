package com.smcapis.smcapis.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FuncionarioDto {

    private Integer rut;
    private String vrut;
    private String paterno;
    private String materno;
    private String email;
    private String codDeptoExt;
    private String nombres;
    private String foto;
    private String departamento;
    private Integer ident;
    private String tipoContrato;
    private String escalafon;
    private int grado;
    private LocalDate fechafin;
    private boolean vigente;
    private LocalDate fechaNacimiento;

}
