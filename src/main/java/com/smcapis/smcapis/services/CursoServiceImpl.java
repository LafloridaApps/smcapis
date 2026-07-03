package com.smcapis.smcapis.services;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.repositories.interfaces.CursoRepository;
import com.smcapis.smcapis.services.interfaces.CursosService;
import com.smcapis.smcapis.dto.CursoFuncionarioDto;
import java.util.List;

@Service
public class CursoServiceImpl implements CursosService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<CursoFuncionarioDto> getCursoByRut(Integer rut, Integer ident) {
        return cursoRepository.getCursoByRut(rut, ident);
    }

}
