package com.smcapis.smcapis.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.dto.PatentesAlcoholDto;
import com.smcapis.smcapis.services.interfaces.PatenteService;

@RestController
@RequestMapping("/api/patentes")
public class PatenteController {

    private final PatenteService patenteService;

    public PatenteController(PatenteService patenteService) {
        this.patenteService = patenteService;
    }

    @GetMapping("/alcohol/{rut}")
    public ResponseEntity<Object> getPatenteByRut(@PathVariable Integer rut) {

        List<PatentesAlcoholDto> list = patenteService.getPatentesAlcoholByRut(rut);
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("message", "no se encotraron registros"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

}
