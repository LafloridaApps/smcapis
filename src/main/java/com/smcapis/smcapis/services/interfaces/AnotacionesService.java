package com.smcapis.smcapis.services.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.AnotacionesFuncionarioDto;

public interface AnotacionesService {

    List<AnotacionesFuncionarioDto> getAnotacionesByRutAndIdent(Integer rut, Integer ident);

}
