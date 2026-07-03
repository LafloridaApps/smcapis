package com.smcapis.smcapis.dto;

import java.time.LocalDate;

public class AnotacionesFuncionarioDto {

    private LocalDate fechaAnotacion;
    private String glosaAnotacion;
    private int anoCalif;
    private String descripsubgrupocalif;
    private String desctipoanotacion;

    private AnotacionesFuncionarioDto(Builder builder) {
        this.fechaAnotacion = builder.fechaAnotacion;
        this.glosaAnotacion = builder.glosaAnotacion;
        this.anoCalif = builder.anoCalif;
        this.descripsubgrupocalif = builder.descripsubgrupocalif;
        this.desctipoanotacion = builder.desctipoanotacion;

    }

    public AnotacionesFuncionarioDto.Builder builder() {
        return new AnotacionesFuncionarioDto.Builder();
    }

    public LocalDate getFechaAnotacion() {
        return fechaAnotacion;
    }

    public void setFechaAnotacion(LocalDate fechaAnotacion) {
        this.fechaAnotacion = fechaAnotacion;
    }

    public String getGlosaAnotacion() {
        return glosaAnotacion;
    }

    public void setGlosaAnotacion(String glosaAnotacion) {
        this.glosaAnotacion = glosaAnotacion;
    }

    public int getAnoCalif() {
        return anoCalif;
    }

    public void setAnoCalif(int anoCalif) {
        this.anoCalif = anoCalif;
    }

    public String getDescripsubgrupocalif() {
        return descripsubgrupocalif;
    }

    public void setDescripsubgrupocalif(String descripsubgrupocalif) {
        this.descripsubgrupocalif = descripsubgrupocalif;
    }

    public String getDesctipoanotacion() {
        return desctipoanotacion;
    }

    public void setDesctipoanotacion(String desctipoanotacion) {
        this.desctipoanotacion = desctipoanotacion;
    }

    public static class Builder {

        private LocalDate fechaAnotacion;
        private String glosaAnotacion;
        private int anoCalif;
        private String descripsubgrupocalif;
        private String desctipoanotacion;

        public Builder fechaAnotacion(LocalDate fechaAnotacion) {
            this.fechaAnotacion = fechaAnotacion;
            return this;

        }

        public Builder glosaAnotacion(String glosaAnotacion) {
            this.glosaAnotacion = glosaAnotacion;
            return this;

        }

        public Builder anoCalif(int anoCalif) {
            this.anoCalif = anoCalif;
            return this;

        }

        public Builder descripsubgrupocalif(String descripsubgrupocalif) {
            this.descripsubgrupocalif = descripsubgrupocalif;
            return this;

        }

        public Builder desctipoanotacion(String desctipoanotacion) {
            this.desctipoanotacion = desctipoanotacion;
            return this;

        }

        public AnotacionesFuncionarioDto build() {
            return new AnotacionesFuncionarioDto(this);
        }

    }

}
