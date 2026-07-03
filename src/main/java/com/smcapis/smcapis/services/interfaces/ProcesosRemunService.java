package com.smcapis.smcapis.services.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.ProcesosRemunDto;

public interface ProcesosRemunService {

    List<ProcesosRemunDto> obtenerProcesos(Integer rut, Integer dominioId, Integer anio);

}
