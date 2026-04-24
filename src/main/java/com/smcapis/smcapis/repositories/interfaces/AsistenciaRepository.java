package com.smcapis.smcapis.repositories.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.smcapis.smcapis.dto.AsistenciaDto;

public interface AsistenciaRepository {

    List<AsistenciaDto> getAsistenciaByRutAndIdent(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin);

}
