package com.smcapis.smcapis.services.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.CursoFuncionarioDto;

public interface CursosService {

    List<CursoFuncionarioDto> getCursoByRut(Integer rut, Integer ident);

}
