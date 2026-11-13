package com.smcapis.smcapis.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.dto.PropietarioNegocio;
import com.smcapis.smcapis.services.interfaces.ConsultaNegocioService;

@RestController
@RequestMapping("/api/negocios")
public class NegocioController {

    private final ConsultaNegocioService consultaNegocioService;

    public NegocioController(ConsultaNegocioService consultaNegocioService) {
        this.consultaNegocioService = consultaNegocioService;
    }

    @GetMapping
    public ResponseEntity<Object> obtenerNegocioRut(@RequestParam Integer rut) {

        PropietarioNegocio negocio = consultaNegocioService.obtenerNegocios(rut);

        return ResponseEntity.status(HttpStatus.OK).body(negocio);
    }

}
