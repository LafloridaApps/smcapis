package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.CursoFuncionarioDto;

public interface CursoRepository {

    List<CursoFuncionarioDto> getCursoByRut(Integer rut, Integer ident);

}
