package com.smcapis.smcapis.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.services.interfaces.LicenciasEntreFechasService;

@RestController
@RequestMapping("/api/funcionario/licencias-entre-fechas")
public class LicenciasEntreFechasController {

    private final LicenciasEntreFechasService licenciasEntreFechasService;

    public LicenciasEntreFechasController(LicenciasEntreFechasService licenciasEntreFechasService) {
        this.licenciasEntreFechasService = licenciasEntreFechasService;
    }

    @GetMapping
    public ResponseEntity<Object> getLicenciasMedicasByDeptos(
            @RequestParam List<String> deptos,
            @RequestParam LocalDate fechaIni,
            @RequestParam LocalDate fechaFin) {

        return ResponseEntity.ok()
                .body(licenciasEntreFechasService.getLicenciasMedicasByDeptos(deptos, fechaIni, fechaFin));
    }

}
