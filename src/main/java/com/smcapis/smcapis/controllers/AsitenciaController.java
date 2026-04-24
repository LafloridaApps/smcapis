package com.smcapis.smcapis.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.dto.AsistenciaDto;
import com.smcapis.smcapis.services.interfaces.AsistenciaService;

import java.util.Map;

@RestController
@RequestMapping("/api/funcionario/asistencia")
public class AsitenciaController {

    private final AsistenciaService asistenciaService;

    public AsitenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping
    public ResponseEntity<Object> getAsistenciaByRutAndIdent(@RequestParam Integer rut, @RequestParam Integer ident,
            @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {

        try {

            List<AsistenciaDto> list = asistenciaService.getAsistenciaByRutAndIdent(rut, ident, fechaInicio, fechaFin);
            if (list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(Map.of("message", "no se encontraron registros"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
