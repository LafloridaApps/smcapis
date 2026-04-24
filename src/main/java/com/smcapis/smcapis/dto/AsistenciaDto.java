package com.smcapis.smcapis.dto;

public class AsistenciaDto {

    private String dia;
    private String fechadia;
    private String horaentman;
    private String horasalman;
    private String horaenttar;
    private String horasaltar;
    private String horentmantj;
    private String horsalmantj;
    private String horenttartj;
    private String horsaltartj;
    private String htrab;
    private String hatr;
    private String hext25;
    private String hext50;
    private String justinasautext;

    public AsistenciaDto(Builder builder) {
        this.dia = builder.dia;
        this.fechadia = builder.fechadia;
        this.horaentman = builder.horaentman;
        this.horasalman = builder.horasalman;
        this.horaenttar = builder.horaenttar;
        this.horasaltar = builder.horasaltar;
        this.horentmantj = builder.horentmantj;
        this.horsalmantj = builder.horsalmantj;
        this.horenttartj = builder.horenttartj;
        this.horsaltartj = builder.horsaltartj;
        this.htrab = builder.htrab;
        this.hext25 = builder.hext25;
        this.hext50 = builder.hext50;
        this.justinasautext = builder.justinasautext;
        this.hatr = builder.hatr;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getFechadia() {
        return fechadia;
    }

    public void setFechadia(String fechadia) {
        this.fechadia = fechadia;
    }

    public String getHoraentman() {
        return horaentman;
    }

    public void setHoraentman(String horaentman) {
        this.horaentman = horaentman;
    }

    public String getHorasalman() {
        return horasalman;
    }

    public void setHorasalman(String horasalman) {
        this.horasalman = horasalman;
    }

    public String getHoraenttar() {
        return horaenttar;
    }

    public void setHoraenttar(String horaenttar) {
        this.horaenttar = horaenttar;
    }

    public String getHorasaltar() {
        return horasaltar;
    }

    public void setHorasaltar(String horasaltar) {
        this.horasaltar = horasaltar;
    }

    public String getHorentmantj() {
        return horentmantj;
    }

    public void setHorentmantj(String horentmantj) {
        this.horentmantj = horentmantj;
    }

    public String getHorsalmantj() {
        return horsalmantj;
    }

    public void setHorsalmantj(String horsalmantj) {
        this.horsalmantj = horsalmantj;
    }

    public String getHorenttartj() {
        return horenttartj;
    }

    public void setHorenttartj(String horenttartj) {
        this.horenttartj = horenttartj;
    }

    public String getHorsaltartj() {
        return horsaltartj;
    }

    public void setHorsaltartj(String horsaltartj) {
        this.horsaltartj = horsaltartj;
    }

    public String getHtrab() {
        return htrab;
    }

    public void setHtrab(String htrab) {
        this.htrab = htrab;
    }

    public String getHext25() {
        return hext25;
    }

    public void setHext25(String hext25) {
        this.hext25 = hext25;
    }

    public String getHext50() {
        return hext50;
    }

    public void setHext50(String hext50) {
        this.hext50 = hext50;
    }

    public String getJustinasautext() {
        return justinasautext;
    }

    public void setJustinasautext(String justinasautext) {
        this.justinasautext = justinasautext;
    }

    
    public static class Builder {

        private String dia;
        private String fechadia;
        private String horaentman;
        private String horasalman;
        private String horaenttar;
        private String horasaltar;
        private String horentmantj;
        private String horsalmantj;
        private String horenttartj;
        private String horsaltartj;
        private String htrab;
        private String hext25;
        private String hext50;
        private String justinasautext;
        private String hatr;

        public Builder dia(String dia) {
            this.dia = dia;
            return this;
        }

        public Builder fechadia(String fechadia) {
            this.fechadia = fechadia;
            return this;
        }

        public Builder horaentman(String horaentman) {
            this.horaentman = horaentman;
            return this;
        }

        public Builder horasalman(String horasalman) {
            this.horasalman = horasalman;
            return this;
        }

        public Builder horaenttar(String horaenttar) {
            this.horaenttar = horaenttar;
            return this;
        }

        public Builder horasaltar(String horasaltar) {
            this.horasaltar = horasaltar;
            return this;
        }

        public Builder horentmantj(String horentmantj) {
            this.horentmantj = horentmantj;
            return this;
        }

        public Builder horsalmantj(String horsalmantj) {
            this.horsalmantj = horsalmantj;
            return this;
        }

        public Builder horenttartj(String horenttartj) {
            this.horenttartj = horenttartj;
            return this;
        }

        public Builder horsaltartj(String horsaltartj) {
            this.horsaltartj = horsaltartj;
            return this;
        }

        public Builder htrab(String htrab) {
            this.htrab = htrab;
            return this;
        }

        public Builder hext25(String hext25) {
            this.hext25 = hext25;
            return this;
        }

        public Builder hext50(String hext50) {
            this.hext50 = hext50;
            return this;
        }

        public Builder justinasautext(String justinasautext) {
            this.justinasautext = justinasautext;
            return this;
        }

        public Builder hatr(String hatr) {
            this.hatr = hatr;
            return this;
        }



        public AsistenciaDto build() {
            return new AsistenciaDto(this);
        }

    }


    public String getHatr() {
        return hatr;
    }

    public void setHatr(String hatr) {
        this.hatr = hatr;
    }

}
