package com.smcapis.smcapis.services.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.LicenciasMedicasFuncionarioDto;

public interface LicenciasMedicasService {

    List<LicenciasMedicasFuncionarioDto> getLicenciasMedicasByRut(Integer rut, Integer ident);

}
