package com.smcapis.smcapis.dto;

import java.util.List;

public class FichaFuncionarioDto {

    private int rut;
    private int ident;

    private List<AnotacionesFuncionarioDto> anotaciones;
    private List<LicenciasMedicasFuncionarioDto> licenciasMedicas;
    private List<CargasFamiliaresDto> cargasFamiliares;
    private List<ProfesionDto> profesiones;
    private List<CursoFuncionarioDto> cursos;
    private List<ContratoDto> contratos;

    private FichaFuncionarioDto(Builder builder) {
        this.rut = builder.rut;
        this.ident = builder.ident;
        this.anotaciones = builder.anotaciones;
        this.licenciasMedicas = builder.licenciasMedicas;
        this.cargasFamiliares = builder.cargasFamiliares;
        this.profesiones = builder.profesiones;
        this.cursos = builder.cursos;
        this.contratos = builder.contratos;
    }

    public FichaFuncionarioDto.Builder builder() {
        return new FichaFuncionarioDto.Builder();
    }

    public int getRut() {
        return rut;
    }

    public int getIdent() {
        return ident;
    }

    public List<AnotacionesFuncionarioDto> getAnotaciones() {
        return anotaciones;
    }

    public List<LicenciasMedicasFuncionarioDto> getLicenciasMedicas() {
        return licenciasMedicas;
    }

    public List<CargasFamiliaresDto> getCargasFamiliares() {
        return cargasFamiliares;
    }

    public List<ProfesionDto> getProfesiones() {
        return profesiones;
    }

    public List<CursoFuncionarioDto> getCursos() {
        return cursos;
    }

    public List<ContratoDto> getContratos() {
        return contratos;
    }

    public static class Builder {
        private int rut;
        private int ident;
        private List<AnotacionesFuncionarioDto> anotaciones;
        private List<LicenciasMedicasFuncionarioDto> licenciasMedicas;
        private List<CargasFamiliaresDto> cargasFamiliares;
        private List<ProfesionDto> profesiones;
        private List<CursoFuncionarioDto> cursos;
        private List<ContratoDto> contratos;

        public Builder rut(int rut) {
            this.rut = rut;
            return this;
        }

        public Builder ident(int ident) {
            this.ident = ident;
            return this;
        }

        public Builder anotaciones(List<AnotacionesFuncionarioDto> anotaciones) {
            this.anotaciones = anotaciones;
            return this;
        }

        public Builder licenciasMedicas(List<LicenciasMedicasFuncionarioDto> licenciasMedicas) {
            this.licenciasMedicas = licenciasMedicas;
            return this;
        }

        public Builder cargasFamiliares(List<CargasFamiliaresDto> cargasFamiliares) {
            this.cargasFamiliares = cargasFamiliares;
            return this;
        }

        public Builder profesiones(List<ProfesionDto> profesiones) {
            this.profesiones = profesiones;
            return this;
        }

        public Builder cursos(List<CursoFuncionarioDto> cursos) {
            this.cursos = cursos;
            return this;
        }

        public Builder contratos(List<ContratoDto> contratos) {
            this.contratos = contratos;
            return this;
        }

        public FichaFuncionarioDto build() {
            return new FichaFuncionarioDto(this);
        }

    }

}
