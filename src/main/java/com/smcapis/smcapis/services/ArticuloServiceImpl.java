package com.smcapis.smcapis.services;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.ArticuloResponse;
import com.smcapis.smcapis.repositories.interfaces.ConsultaArticuloRepository;
import com.smcapis.smcapis.services.interfaces.ArticuloService;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    private final ConsultaArticuloRepository consultaArticuloRepository;

    public ArticuloServiceImpl(ConsultaArticuloRepository consultaArticuloRepository) {
        this.consultaArticuloRepository = consultaArticuloRepository;
    }

    @Override
    public ArticuloResponse getArticuloXcodInv(int condinv) {

        return consultaArticuloRepository.obtenerCodigoXcorrelatico(condinv);
    }

}
