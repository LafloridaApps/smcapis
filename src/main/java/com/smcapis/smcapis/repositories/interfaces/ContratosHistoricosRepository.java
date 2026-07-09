package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.ContratoDto;

public interface ContratosHistoricosRepository {

    List<ContratoDto> getContratosByRut(Integer rut);


}
