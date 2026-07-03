package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.LicenciasMedicasFuncionarioDto;

public interface LicenciasMedicasRepository {

    List<LicenciasMedicasFuncionarioDto> getLicenciasMedicasByRut(Integer rut, Integer ident);

}
