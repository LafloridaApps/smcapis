package com.smcapis.smcapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.ContratoDto;
import com.smcapis.smcapis.repositories.interfaces.ContratosHistoricosRepository;
import com.smcapis.smcapis.services.interfaces.ContratosHistoricosService;

@Service
public class ContratoHistoricoServiceImpl implements ContratosHistoricosService {

    private final ContratosHistoricosRepository contratoHistoricosRepository;

    public ContratoHistoricoServiceImpl(ContratosHistoricosRepository contratoHistoricosRepository) {
        this.contratoHistoricosRepository = contratoHistoricosRepository;
    }

    @Override
    public List<ContratoDto> getContratosByRut(Integer rut) {
        return contratoHistoricosRepository.getContratosByRut(rut);
    }

}
