package com.smcapis.smcapis.services.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.ProfesionDto;

public interface ProfesionesService {

    List<ProfesionDto> getProfesionesByRut(Integer rut);

}
