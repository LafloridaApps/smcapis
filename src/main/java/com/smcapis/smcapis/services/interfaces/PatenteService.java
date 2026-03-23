package com.smcapis.smcapis.services.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.PatentesAlcoholDto;

public interface PatenteService {

    List<PatentesAlcoholDto> getPatentesAlcoholByRut(Integer rut);
}
