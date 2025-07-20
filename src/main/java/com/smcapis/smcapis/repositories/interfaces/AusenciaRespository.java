package com.smcapis.smcapis.repositories.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.smcapis.smcapis.dto.AusenciasResponse;

public interface AusenciaRespository {

    List<AusenciasResponse> getAusenciasByRutAndIdent(Integer rut, Integer ident, LocalDate fechaInicio, LocalDate fechaFin);

}
