package com.smcapis.smcapis.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.services.interfaces.FotoArticuloService;

@RestController
@RequestMapping("/api/foto")
public class FotoController {

    private final FotoArticuloService fotoArticuloService;

    public FotoController(FotoArticuloService fotoArticuloService) {
        this.fotoArticuloService = fotoArticuloService;
    }

    @GetMapping("/articulo")
    public ResponseEntity<Object> getFotoArticulo(@RequestParam String codigo, @RequestParam Integer correlativo) {
        String foto = fotoArticuloService.getFotoArticulo(codigo, correlativo);
        if (foto == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(Map.of("foto", foto));
    }

}
