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
        if (codigo == null || !codigo.trim().matches("\\d{8}")) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST, "El código debe tener 8 dígitos");
        }
        return codigoRepository.getCodigo(codigo.trim());
    }

}
