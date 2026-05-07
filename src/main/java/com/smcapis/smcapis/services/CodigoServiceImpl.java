package com.smcapis.smcapis.services;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.CodigoResponse;
import com.smcapis.smcapis.repositories.interfaces.CodigoRepository;
import com.smcapis.smcapis.services.interfaces.CodigoService;

@Service
public class CodigoServiceImpl implements CodigoService {

    private final CodigoRepository codigoRepository;

    public CodigoServiceImpl(CodigoRepository codigoRepository) {
        this.codigoRepository = codigoRepository;
    }

    @Override
    public CodigoResponse obtenerCodigoInventario(String codigo) {
        return codigoRepository.getCodigo(codigo);
    }

}
