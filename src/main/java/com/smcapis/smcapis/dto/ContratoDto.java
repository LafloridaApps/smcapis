package com.smcapis.smcapis.dto;

import java.time.LocalDate;

public class ContratoDto {

    private String departamento;
    private int ident;
    private String tipoContrato;
    private int grado;
    private String nombreescalafon;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaResolContr;
    private String numeroSolContr;
    private String obscontre;

    public static Builder builder() {
        return new Builder();
    }

    public ContratoDto(Builder builder) {
        this.departamento = builder.departamento;
        this.ident = builder.ident;
        this.tipoContrato = builder.tipoContrato;
        this.grado = builder.grado;
        this.nombreescalafon = builder.nombreescalafon;
        this.fechaInicio = builder.fechaInicio;
        this.fechaFin = builder.fechaFin;
        this.fechaResolContr = builder.fechaResolContr;
        this.numeroSolContr = builder.numeroSolContr;
        this.obscontre = builder.obscontre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getIdent() {
        return ident;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public int getGrado() {
        return grado;
    }

    public String getNombreescalafon() {
        return nombreescalafon;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalDate getFechaResolContr() {
        return fechaResolContr;
    }

    public String getNumeroSolContr() {
        return numeroSolContr;
    }

    public String getObscontre() {
        return obscontre;
    }

    public static class Builder {

        private String departamento;
        private int ident;
        private String tipoContrato;
        private int grado;
        private String nombreescalafon;
        private LocalDate fechaInicio;
        private LocalDate fechaFin;
        private LocalDate fechaResolContr;
        private String numeroSolContr;
        private String obscontre;

        public Builder departamento(String departamento) {
            this.departamento = departamento;
            return this;
        }

        public Builder ident(int ident) {
            this.ident = ident;
            return this;
        }

        public Builder tipoContrato(String tipoContrato) {
            this.tipoContrato = tipoContrato;
            return this;
        }

        public Builder grado(int grado) {
            this.grado = grado;
            return this;
        }

        public Builder nombreescalafon(String nombreescalafon) {
            this.nombreescalafon = nombreescalafon;
            return this;
        }

        public Builder fechaInicio(LocalDate fechaInicio) {
            this.fechaInicio = fechaInicio;
            return this;
        }

        public Builder fechaFin(LocalDate fechaFin) {
            this.fechaFin = fechaFin;
            return this;
        }

        public Builder fechaResolContr(LocalDate fechaResolContr) {
            this.fechaResolContr = fechaResolContr;
            return this;
        }

        public Builder numeroSolContr(String numeroSolContr) {
            this.numeroSolContr = numeroSolContr;
            return this;
        }

        public Builder obscontre(String obscontre) {
            this.obscontre = obscontre;
            return this;
        }

        public ContratoDto build() {
            return new ContratoDto(this);
        }

    }

}
