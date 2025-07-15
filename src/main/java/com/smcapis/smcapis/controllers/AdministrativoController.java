package com.smcapis.smcapis.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.services.interfaces.AdministrativoService;

@RestController
@RequestMapping("/api/funcionario/administrativos")
public class AdministrativoController {

    private final AdministrativoService administrativoService;

    public AdministrativoController(AdministrativoService administrativoService) {
        this.administrativoService = administrativoService;
    }

    @GetMapping
    public ResponseEntity<Object> getAdministrativosByRutAndIdent(@RequestParam Integer rut,
            @RequestParam Integer ident) {

        return ResponseEntity.status(HttpStatus.OK).body(administrativoService.getAdminByRutAndIdent(rut, ident));
    }

}
