package com.smcapis.smcapis.services.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.smcapis.smcapis.dto.AsistenciaDto;
import com.smcapis.smcapis.dto.NewAsistenciaDto;

public interface AsistenciaService {

    List<AsistenciaDto> getAsistenciaByRutAndIdent(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin);

    List<NewAsistenciaDto> getAsistenciaRut(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin);

}
