package com.smcapis.smcapis.dto;

import java.time.LocalDate;
import java.util.List;

public record ArticuloResponse(
        int nroFila,
        Integer coddocaf,
        Integer numerodocaf,
        Integer anodocaf,
        Integer rut,
        Integer nroOrden,
        LocalDate fechaOrden,
        Integer nroFactura,
        LocalDate fechaFactura,
        String codgrupoart,
        String codsubgrupoart,
        String codrubroart,
        String codart,
        String codinv,
        Integer vutilinv,
        Integer valorinv,
        LocalDate fechaAlta,
        String depto,
        String nombreDepartamento,
        String jefeDeparamento,
        String nombreOficina,
        String responsable,
        String cargo,
        String nombreArticulo,
        List<ArticuloDatosAdicionales> datosAdicionales

) {

}
