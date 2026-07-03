package com.smcapis.smcapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.LicenciasMedicasFuncionarioDto;
import com.smcapis.smcapis.repositories.interfaces.LicenciasMedicasRepository;
import com.smcapis.smcapis.services.interfaces.LicenciasMedicasService;

@Service
public class LicenciasMedicasServiceImpl implements LicenciasMedicasService {

    private final LicenciasMedicasRepository licenciasMedicasRepository;

    public LicenciasMedicasServiceImpl(LicenciasMedicasRepository licenciasMedicasRepository) {
        this.licenciasMedicasRepository = licenciasMedicasRepository;
    }

    @Override
    public List<LicenciasMedicasFuncionarioDto> getLicenciasMedicasByRut(Integer rut, Integer ident) {
        return licenciasMedicasRepository.getLicenciasMedicasByRut(rut, ident);
    }

}
