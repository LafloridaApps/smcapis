package com.smcapis.smcapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.ProcesosRemunDto;
import com.smcapis.smcapis.repositories.interfaces.ProcesosRemunRepository;
import com.smcapis.smcapis.services.interfaces.ProcesosRemunService;

@Service
public class ProcesosRemunServiceImpl implements ProcesosRemunService {

    private final ProcesosRemunRepository procesRemunRepository;

    public ProcesosRemunServiceImpl(ProcesosRemunRepository procesosRemunRepository) {
        this.procesRemunRepository = procesosRemunRepository;
    }

    @Override
    public List<ProcesosRemunDto> obtenerProcesos(Integer rut, Integer dominioId, Integer anio) {
        return procesRemunRepository.obtenerProcesos(rut, dominioId, anio);
    }

}
