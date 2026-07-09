package com.smcapis.smcapis.services.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.ContratoDto;

public interface ContratosHistoricosService {

    List<ContratoDto> getContratosByRut(Integer rut);

}
