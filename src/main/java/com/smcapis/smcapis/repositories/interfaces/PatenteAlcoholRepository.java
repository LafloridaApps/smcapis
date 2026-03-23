package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.PatentesAlcoholDto;

public interface PatenteAlcoholRepository {

    List<PatentesAlcoholDto> getPatentesAlcoholByRut(Integer rut);

}
