package com.smcapis.smcapis.dto;

public class ProfesionDto {

    private String profesion;

    public ProfesionDto(Builder builder) {
        this.profesion = builder.profesion;
    }

    public ProfesionDto.Builder builder() {
        return new ProfesionDto.Builder();
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public static class Builder {
        private String profesion;

        public Builder profesion(String profesion) {
            this.profesion = profesion;
            return this;
        }

        public ProfesionDto build() {
            return new ProfesionDto(this);
        }
    }

}
