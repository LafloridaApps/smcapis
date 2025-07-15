package com.smcapis.smcapis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.services.interfaces.FeriadosService;

@RestController
@RequestMapping("/api/funcionario/feriados")
public class FeriadoController {

    private final FeriadosService feriadosService;

    public FeriadoController(FeriadosService feriadosService) {
        this.feriadosService = feriadosService;
    }

    @GetMapping
    public ResponseEntity<Object> getFeriadosByRutAndIdent(@RequestParam Integer rut, @RequestParam Integer ident) {

        return ResponseEntity.ok().body(feriadosService.getFeriadoByRutAndIdent(rut, ident));
    }

}
