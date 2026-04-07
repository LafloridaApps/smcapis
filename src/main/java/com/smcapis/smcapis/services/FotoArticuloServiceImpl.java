package com.smcapis.smcapis.services;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.repositories.interfaces.FotoArticuloRepository;
import com.smcapis.smcapis.services.interfaces.FotoArticuloService;

@Service
public class FotoArticuloServiceImpl implements FotoArticuloService {

    private final FotoArticuloRepository fotoArticuloRepository;

    public FotoArticuloServiceImpl(FotoArticuloRepository fotoArticuloRepository) {
        this.fotoArticuloRepository = fotoArticuloRepository;
    }

    @Override
    public String getFotoArticulo(String codigo, int correlativo) {
        return fotoArticuloRepository.fotoArticulo(codigo, correlativo);
    }

}
