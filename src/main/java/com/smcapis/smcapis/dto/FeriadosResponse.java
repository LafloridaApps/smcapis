package com.smcapis.smcapis.dto;

import java.util.List;

public class FeriadosResponse {

    private List<ResumenFeriadoLegal> resumenes;
    private List<DetalleFeriadoLegal> detalles;

    public List<ResumenFeriadoLegal> getResumenes() {
        return resumenes;
    }

    public void setResumenes(List<ResumenFeriadoLegal> resumenes) {
        this.resumenes = resumenes;
    }

    public List<DetalleFeriadoLegal> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFeriadoLegal> detalles) {
        this.detalles = detalles;
    }

}
