package com.smcapis.smcapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.DepartamentoDto;
import com.smcapis.smcapis.repositories.interfaces.DepartamentosRepository;
import com.smcapis.smcapis.services.interfaces.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentosRepository departamentosRepository;

    public DepartamentoServiceImpl(DepartamentosRepository departamentosRepository) {
        this.departamentosRepository = departamentosRepository;
    }

    @Override
    public List<DepartamentoDto> getDepartamentos() {
        return departamentosRepository.getDepartamentos();
    }

}