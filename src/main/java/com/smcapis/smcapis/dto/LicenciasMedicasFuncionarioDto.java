package com.smcapis.smcapis.dto;

import java.time.LocalDate;

public class LicenciasMedicasFuncionarioDto {

    private LocalDate fechaInicio;
    private LocalDate fechaRecepcion;
    private long numlic;
    private int diaslic;
    private String tipoLicencia;

    private LicenciasMedicasFuncionarioDto(Builder builder) {
        this.fechaInicio = builder.fechaInicio;
        this.fechaRecepcion = builder.fechaRecepcion;
        this.numlic = builder.numlic;
        this.diaslic = builder.diaslic;
        this.tipoLicencia = builder.tipoLicencia;

    }

    public LicenciasMedicasFuncionarioDto.Builder builder() {
        return new LicenciasMedicasFuncionarioDto.Builder();
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDate fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public long getNumlic() {
        return numlic;
    }

    public void setNumlic(long numlic) {
        this.numlic = numlic;
    }

    public int getDiaslic() {
        return diaslic;
    }

    public void setDiaslic(int diaslic) {
        this.diaslic = diaslic;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public static class Builder {

        private LocalDate fechaInicio;
        private LocalDate fechaRecepcion;
        private long numlic;
        private int diaslic;
        private String tipoLicencia;

        public Builder fechaInicio(LocalDate fechaInicio) {
            this.fechaInicio = fechaInicio;
            return this;
        }

        public Builder fechaRecepcion(LocalDate fechaRecepcion) {
            this.fechaRecepcion = fechaRecepcion;
            return this;

        }

        public Builder numlic(long numlic) {
            this.numlic = numlic;
            return this;
        }

        public Builder diaslic(int diaslic) {
            this.diaslic = diaslic;
            return this;
        }

        public Builder tipoLicencia(String tipoLicencia) {
            this.tipoLicencia = tipoLicencia;
            return this;
        }

        public LicenciasMedicasFuncionarioDto build() {
            return new LicenciasMedicasFuncionarioDto(this);
        }

    }

}
