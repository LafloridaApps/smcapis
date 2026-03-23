package com.smcapis.smcapis.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.dto.DepartamentoDto;
import com.smcapis.smcapis.services.interfaces.DepartamentoService;

@RestController
@RequestMapping("/api/funcionario/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public ResponseEntity<Object> getDepartamentos() {

        List<DepartamentoDto> deptos = departamentoService.getDepartamentos();

        if (deptos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "no existen departamentos"));

        }

        return ResponseEntity.status(HttpStatus.OK).body(deptos);

    }

}
