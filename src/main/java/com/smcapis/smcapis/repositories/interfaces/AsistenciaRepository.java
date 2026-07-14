package com.smcapis.smcapis.repositories.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.smcapis.smcapis.dto.AsistenciaDto;
import com.smcapis.smcapis.dto.NewAsistenciaDto;

public interface AsistenciaRepository {

    List<AsistenciaDto> getAsistenciaByRutAndIdent(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin);

    List<NewAsistenciaDto> obtenerAsistenciaRut(Integer rut, Integer ident, LocalDate fechaInicio, LocalDate fechaFin);

}
