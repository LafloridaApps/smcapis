package com.smcapis.smcapis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.services.FuncionarioServiceImpl;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

    private final FuncionarioServiceImpl funcionarioService;

    public FuncionarioController(FuncionarioServiceImpl funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Object> getFuncionarioByRut(@PathVariable Integer rut) {

        return ResponseEntity.ok(funcionarioService.getFuncionarioByRut(rut));

    }

}
