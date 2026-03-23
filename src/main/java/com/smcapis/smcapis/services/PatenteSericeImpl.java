package com.smcapis.smcapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.PatentesAlcoholDto;
import com.smcapis.smcapis.repositories.interfaces.PatenteAlcoholRepository;
import com.smcapis.smcapis.services.interfaces.PatenteService;

@Service
public class PatenteSericeImpl implements PatenteService {

    private final PatenteAlcoholRepository patentoAlcoholRepository;

    public PatenteSericeImpl(PatenteAlcoholRepository patenteAlcoholRepository) {
        this.patentoAlcoholRepository = patenteAlcoholRepository;
    }

    @Override
    public List<PatentesAlcoholDto> getPatentesAlcoholByRut(Integer rut) {
        return patentoAlcoholRepository.getPatentesAlcoholByRut(rut);
    }

}
