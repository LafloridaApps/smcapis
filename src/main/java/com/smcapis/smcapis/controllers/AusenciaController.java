package com.smcapis.smcapis.controllers;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.services.interfaces.AusenciasService;

@RestController
@RequestMapping("/api/funcionario/ausencias")
public class AusenciaController {

    private final AusenciasService ausenciasService;

    public AusenciaController(AusenciasService ausenciasService) {
        this.ausenciasService = ausenciasService;
    }

    @GetMapping
    public ResponseEntity<Object> getAusenciasBetweenFechas(@RequestParam Integer rut,
            @RequestParam Integer ident, @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {

        return ResponseEntity.ok()
                .body(ausenciasService.getAusenciaBetweenDatesByRutAndIdent(rut, ident, fechaInicio, fechaFin));
    }

}
