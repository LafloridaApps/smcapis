package com.smcapis.smcapis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smcapis.smcapis.services.CumpleService;
import com.smcapis.smcapis.services.FuncionarioServiceImpl;
import com.smcapis.smcapis.services.interfaces.FuncionarioListService;
import com.smcapis.smcapis.services.interfaces.FuncionarioService;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin(origins = {"https://intranet.laflorida.cl"})
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final FuncionarioListService funcionarioListService;
    private final CumpleService cumpleService;



    public FuncionarioController(FuncionarioServiceImpl funcionarioService, FuncionarioListService funcionarioListService, CumpleService cumpleService) {
        this.funcionarioService = funcionarioService;
        this.funcionarioListService = funcionarioListService;
        this.cumpleService = cumpleService;
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Object> getFuncionarioByRut(@PathVariable Integer rut) {

        return ResponseEntity.ok(funcionarioService.getFuncionarioByRut(rut));

    }

     @GetMapping("/{rut}/list")
    public ResponseEntity<Object> getFuncionarioListByRut(@PathVariable Integer rut) {

        return ResponseEntity.ok(funcionarioListService.getFuncionario(rut));

    }

    @GetMapping("/cumple-list")
    public ResponseEntity<Object> getCumpleMensuales(){
        return ResponseEntity.ok(cumpleService.obtenerCumpleMes());
    }

}
