package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.AnotacionesFuncionarioDto;

public interface AnotacionesRepository {

    List<AnotacionesFuncionarioDto> getAnotacionesByRutAndIdent(Integer rut, Integer ident);

}
