package com.smcapis.smcapis.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.dto.ArticuloResponse;
import com.smcapis.smcapis.services.interfaces.InventarioService;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService inventarioService;
    private static final String KEY_VALUE = "message";

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<Object> getArticuloByCodigo(@RequestParam String codigo, @RequestParam Integer correlativo) {

        try {

            ArticuloResponse response = inventarioService.getArticuloByCodigo(codigo, correlativo);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(Map.of(KEY_VALUE, "no se encontraron registros"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/depto")
    public ResponseEntity<Object> getArticuloByDepto(
            @RequestParam(required = true) String depto,
            @RequestParam(required = false) Integer linoficina) {
        try {
            List<ArticuloResponse> response = inventarioService.getArticuloByDepto(depto, linoficina);
            if (response.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(Map.of(KEY_VALUE, "no se encontraron registros"));
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/oficinas")
    public ResponseEntity<Object> getOficinasByCodigoDepto(@RequestParam String depto) {
        if (depto == null || depto.isEmpty()) {

            return ResponseEntity.status(HttpStatus.OK).body(Map.of(KEY_VALUE, "no existen departamentos"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(inventarioService.getOficinasByCodigoDepto(depto));
    }

}
