package com.smcapis.smcapis.services.interfaces;

import java.time.LocalDate;
import java.util.List;


import com.smcapis.smcapis.dto.AusenciasResponse;

public interface AusenciasService {

    List<AusenciasResponse> getAusenciaBetweenDatesByRutAndIdent(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin);

}
