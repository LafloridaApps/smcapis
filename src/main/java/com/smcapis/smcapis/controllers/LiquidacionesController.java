package com.smcapis.smcapis.controllers;

import com.smcapis.smcapis.services.interfaces.LiquidacionesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liquidaciones")
@CrossOrigin(origins = {"https://intranet.laflorida.cl"})
public class LiquidacionesController {

    private final LiquidacionesService liquidacionesServicio;

    public LiquidacionesController(LiquidacionesService liquidacionesServicio) {
        this.liquidacionesServicio = liquidacionesServicio;
    }

    @GetMapping("/detalle")
    public ResponseEntity<Object> obtenerDetalle(
            @RequestParam Integer rut,
            @RequestParam("dominio_id") Integer dominioId,
            @RequestParam Integer anio,
            @RequestParam Integer mes,
            @RequestParam("proceso_id") List<Integer> procesoId,
            @RequestParam(defaultValue = "false") boolean consolidado) {
        if (consolidado) {
            return ResponseEntity.ok(liquidacionesServicio.obtenerDetalleConsolidado(rut, dominioId, anio, mes, procesoId));
        }
        return ResponseEntity.ok(liquidacionesServicio.obtenerDetalle(rut, dominioId, anio, mes, procesoId.getFirst()));
    }

}
