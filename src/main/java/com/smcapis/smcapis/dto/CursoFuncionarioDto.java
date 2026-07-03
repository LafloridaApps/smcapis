package com.smcapis.smcapis.dto;

import java.time.LocalDate;

public class CursoFuncionarioDto {

    private LocalDate fechaInicioCurso;
    private LocalDate fechaTerminoCurso;
    private String descripcionTipoCurso;
    private String institucion;
    private String nivelTecnico;
    private String descripcionCurso;
    private int horas;
    private double nota;
    private String materiaCurso;

    private CursoFuncionarioDto(Builder builder) {

        this.fechaInicioCurso = builder.fechaInicioCurso;
        this.fechaTerminoCurso = builder.fechaTerminoCurso;
        this.descripcionTipoCurso = builder.descripcionTipoCurso;
        this.institucion = builder.institucion;
        this.nivelTecnico = builder.nivelTecnico;
        this.descripcionCurso = builder.descripcionCurso;
        this.horas = builder.horas;
        this.nota = builder.nota;
        this.materiaCurso = builder.materiaCurso;

    }

    public CursoFuncionarioDto.Builder builder() {
        return new CursoFuncionarioDto.Builder();
    }

    public LocalDate getFechaInicioCurso() {
        return fechaInicioCurso;
    }

    public void setFechaInicioCurso(LocalDate fechaInicioCurso) {
        this.fechaInicioCurso = fechaInicioCurso;
    }

    public LocalDate getFechaTerminoCurso() {
        return fechaTerminoCurso;
    }

    public void setFechaTerminoCurso(LocalDate fechaTerminoCurso) {
        this.fechaTerminoCurso = fechaTerminoCurso;
    }

    public String getDescripcionTipoCurso() {
        return descripcionTipoCurso;
    }

    public void setDescripcionTipoCurso(String descripcionTipoCurso) {
        this.descripcionTipoCurso = descripcionTipoCurso;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getNivelTecnico() {
        return nivelTecnico;
    }

    public void setNivelTecnico(String nivelTecnico) {
        this.nivelTecnico = nivelTecnico;
    }

    public String getDescripcionCurso() {
        return descripcionCurso;
    }

    public void setDescripcionCurso(String descripcionCurso) {
        this.descripcionCurso = descripcionCurso;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getMateriaCurso() {
        return materiaCurso;
    }

    public void setMateriaCurso(String materiaCurso) {
        this.materiaCurso = materiaCurso;
    }

    public static class Builder {

        private LocalDate fechaInicioCurso;
        private LocalDate fechaTerminoCurso;
        private String descripcionTipoCurso;
        private String institucion;
        private String nivelTecnico;
        private String descripcionCurso;
        private int horas;
        private double nota;
        private String materiaCurso;

        public Builder fechaInicioCurso(LocalDate fechaInicioCurso) {
            this.fechaInicioCurso = fechaInicioCurso;
            return this;

        }

        public Builder fechaTerminoCurso(LocalDate fechaTerminoCurso) {
            this.fechaTerminoCurso = fechaTerminoCurso;
            return this;

        }

        public Builder descripcionTipoCurso(String descripcionTipoCurso) {
            this.descripcionTipoCurso = descripcionTipoCurso;
            return this;

        }

        public Builder institucion(String institucion) {
            this.institucion = institucion;
            return this;

        }

        public Builder nivelTecnico(String nivelTecnico) {
            this.nivelTecnico = nivelTecnico;
            return this;

        }

        public Builder descripcionCurso(String descripcionCurso) {
            this.descripcionCurso = descripcionCurso;
            return this;

        }

        public Builder horas(int horas) {
            this.horas = horas;
            return this;

        }

        public Builder nota(double nota) {
            this.nota = nota;
            return this;

        }

        public Builder materiaCurso(String materiaCurso) {
            this.materiaCurso = materiaCurso;
            return this;

        }

        public CursoFuncionarioDto build() {
            return new CursoFuncionarioDto(this);
        }

    }

}
