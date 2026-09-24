package com.smcapis.smcapis.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smcapis.smcapis.dto.CatalogoArticuloResponse;
import com.smcapis.smcapis.services.interfaces.CatalogoArticuloService;

@RestController
@RequestMapping("/api/inventario/catalogo-articulos")
public class CatalogoArticuloController {

    private final CatalogoArticuloService service;

    public CatalogoArticuloController(CatalogoArticuloService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CatalogoArticuloResponse>> obtenerArticulos() {
        return ResponseEntity.ok(service.obtenerArticulos());
    }
}
