package com.smcapis.smcapis.dto;

import java.time.LocalDate;

public class CargasFamiliaresDto {

    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private LocalDate fechaNacimiento;
    private LocalDate fechaInicio;
    private LocalDate fechaTermino;
    private LocalDate fechaResol;
    private String resol;
    private String parentesco;

    private CargasFamiliaresDto(Builder builder) {
        this.apellidoPaterno = builder.apellidoPaterno;
        this.apellidoMaterno = builder.apellidoMaterno;
        this.nombres = builder.nombres;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.fechaInicio = builder.fechaInicio;
        this.fechaTermino = builder.fechaTermino;
        this.fechaResol = builder.fechaResol;
        this.resol = builder.resol;
        this.parentesco = builder.parentesco;
    }

    public CargasFamiliaresDto.Builder builder() {
        return new CargasFamiliaresDto.Builder();
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    public LocalDate getFechaResol() {
        return fechaResol;
    }

    public String getResol() {
        return resol;
    }

    public String getParentesco() {
        return parentesco;
    }

    public static class Builder {
        private String apellidoPaterno;
        private String apellidoMaterno;
        private String nombres;
        private LocalDate fechaNacimiento;
        private LocalDate fechaInicio;
        private LocalDate fechaTermino;
        private LocalDate fechaResol;
        private String resol;
        private String parentesco;

        public Builder apellidoPaterno(String apellidoPaterno) {
            this.apellidoPaterno = apellidoPaterno;
            return this;
        }

        public Builder apellidoMaterno(String apellidoMaterno) {
            this.apellidoMaterno = apellidoMaterno;
            return this;
        }

        public Builder nombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public Builder fechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Builder fechaInicio(LocalDate fechaInicio) {
            this.fechaInicio = fechaInicio;
            return this;
        }

        public Builder fechaTermino(LocalDate fechaTermino) {
            this.fechaTermino = fechaTermino;
            return this;
        }

        public Builder fechaResol(LocalDate fechaResol) {
            this.fechaResol = fechaResol;
            return this;
        }

        public Builder resol(String resol) {
            this.resol = resol;
            return this;
        }

        public Builder parentesco(String parentesco) {
            this.parentesco = parentesco;
            return this;
        }

        public CargasFamiliaresDto build() {
            return new CargasFamiliaresDto(this);
        }

    }

}
