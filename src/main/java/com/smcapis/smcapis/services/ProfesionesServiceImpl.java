package com.smcapis.smcapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.ProfesionDto;
import com.smcapis.smcapis.repositories.interfaces.ProfesionesRepository;
import com.smcapis.smcapis.services.interfaces.ProfesionesService;

@Service
public class ProfesionesServiceImpl implements ProfesionesService {

    private final ProfesionesRepository profesionesRepository;

    public ProfesionesServiceImpl(ProfesionesRepository profesionesRepository) {
        this.profesionesRepository = profesionesRepository;
    }

    @Override
    public List<ProfesionDto> getProfesionesByRut(Integer rut) {
        return profesionesRepository.getProfesionFuncionario(rut);
    }

}
