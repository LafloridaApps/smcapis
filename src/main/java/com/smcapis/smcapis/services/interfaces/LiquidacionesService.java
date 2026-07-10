package com.smcapis.smcapis.services.interfaces;

import com.smcapis.smcapis.dto.RespuestaLiquidacionDetalle;

import java.util.List;

public interface LiquidacionesService {

    RespuestaLiquidacionDetalle obtenerDetalle(Integer rut, Integer dominioId, Integer anio, Integer mes, Integer procesoId);

    RespuestaLiquidacionDetalle obtenerDetalleConsolidado(Integer rut, Integer dominioId, Integer anio, Integer mes, List<Integer> procesoIds);

}
