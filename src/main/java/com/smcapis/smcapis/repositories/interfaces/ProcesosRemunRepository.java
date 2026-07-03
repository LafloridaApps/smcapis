package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.ProcesosRemunDto;

public interface ProcesosRemunRepository {

    List<ProcesosRemunDto> obtenerProcesos(Integer rut, Integer dominioId, Integer anio);

}
