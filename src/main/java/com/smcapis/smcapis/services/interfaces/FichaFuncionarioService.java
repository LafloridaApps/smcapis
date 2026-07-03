package com.smcapis.smcapis.services.interfaces;

import com.smcapis.smcapis.dto.FichaFuncionarioDto;

public interface FichaFuncionarioService {

    FichaFuncionarioDto getFichaByRut(Integer rut, Integer ident);

}
