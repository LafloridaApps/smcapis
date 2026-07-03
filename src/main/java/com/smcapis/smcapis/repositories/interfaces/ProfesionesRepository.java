package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.ProfesionDto;

public interface ProfesionesRepository {

    List<ProfesionDto> getProfesionFuncionario(Integer rut);

}
