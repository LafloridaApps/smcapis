package com.smcapis.smcapis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.services.interfaces.ProcesosRemunService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/procesos")
public class ProcesosRemunController {

    private final ProcesosRemunService procesosRemunService;

    public ProcesosRemunController(ProcesosRemunService procesosRemunService) {
        this.procesosRemunService = procesosRemunService;
    }

    @GetMapping("/remuneraciones")
    public ResponseEntity<Object> obtenerProcesosRemuneraciones(
            @RequestParam Integer rut,
            @RequestParam("dominio_id") Integer dominioId,
            @RequestParam Integer anio) {
        return ResponseEntity.ok(procesosRemunService.obtenerProcesos(rut, dominioId, anio));
    }


}
