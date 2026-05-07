package com.smcapis.smcapis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.dto.CodigoResponse;
import com.smcapis.smcapis.services.interfaces.CodigoService;

@RestController
@RequestMapping("/api/inventario")
public class CodigoController {

    private final CodigoService codigoService;

    public CodigoController(CodigoService codigoService) {
        this.codigoService = codigoService;
    }

    @GetMapping("/codigo")
    public ResponseEntity<Object> obtenerCodigoInventario(@RequestParam String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        CodigoResponse codart = codigoService.obtenerCodigoInventario(codigo);
        return ResponseEntity.ok().body(codart);
    }

}
