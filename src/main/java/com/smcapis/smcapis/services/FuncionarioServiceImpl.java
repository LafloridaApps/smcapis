package com.smcapis.smcapis.services;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.FuncionarioDto;
import com.smcapis.smcapis.repositories.interfaces.FuncionarioRespository;
import com.smcapis.smcapis.services.interfaces.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRespository funcionarioRespository;

    public FuncionarioServiceImpl(FuncionarioRespository funcionarioRespository) {
        this.funcionarioRespository = funcionarioRespository;
    }

    @Override
    public FuncionarioDto getFuncionarioByRut(Integer rut) {
        return funcionarioRespository.getFuncionario(rut);
    }

}
