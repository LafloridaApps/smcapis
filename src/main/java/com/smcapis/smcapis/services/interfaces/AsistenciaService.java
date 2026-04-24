package com.smcapis.smcapis.services.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.smcapis.smcapis.dto.AsistenciaDto;

public interface AsistenciaService {

    List<AsistenciaDto> getAsistenciaByRutAndIdent(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin);

}
