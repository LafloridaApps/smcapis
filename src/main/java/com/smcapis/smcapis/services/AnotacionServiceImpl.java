package com.smcapis.smcapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.AnotacionesFuncionarioDto;
import com.smcapis.smcapis.repositories.interfaces.AnotacionesRepository;
import com.smcapis.smcapis.services.interfaces.AnotacionesService;

@Service
public class AnotacionServiceImpl implements AnotacionesService {

    private final AnotacionesRepository anotacionesRepository;

    public AnotacionServiceImpl(
            com.smcapis.smcapis.repositories.interfaces.AnotacionesRepository anotacionesRepository) {
        this.anotacionesRepository = anotacionesRepository;
    }

    @Override
    public List<AnotacionesFuncionarioDto> getAnotacionesByRutAndIdent(Integer rut, Integer ident) {
        return anotacionesRepository.getAnotacionesByRutAndIdent(rut, ident);
    }

}
