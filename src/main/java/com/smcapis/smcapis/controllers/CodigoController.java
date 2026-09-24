package com.smcapis.smcapis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.dto.ArticuloResponse;
import com.smcapis.smcapis.dto.CodigoResponse;
import com.smcapis.smcapis.services.interfaces.ArticuloService;
import com.smcapis.smcapis.services.interfaces.CodigoService;

@RestController
@RequestMapping("/api/inventario")
public class CodigoController {

    private final CodigoService codigoService;
    private final ArticuloService articuloService;

    public CodigoController(CodigoService codigoService, ArticuloService articuloService) {
        this.codigoService = codigoService;
        this.articuloService = articuloService;
    }

    @GetMapping("/codigo")
    public ResponseEntity<Object> obtenerCodigoInventario(@RequestParam String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        CodigoResponse codart = codigoService.obtenerCodigoInventario(codigo);
        return codart == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(codart);
    }

    @GetMapping("/articulo/{codinv}")
    public ResponseEntity<Object> obtenerArticulo(@PathVariable Integer codinv) {
        if (codinv == null) {
            return ResponseEntity.badRequest().build();
        }

        ArticuloResponse articulo = articuloService.getArticuloXcodInv(codinv);
        return ResponseEntity.ok().body(articulo);
    }

}
