package com.smcapis.smcapis.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.smcapis.smcapis.dto.CatalogoArticuloResponse;
import com.smcapis.smcapis.repositories.interfaces.CatalogoArticuloRepository;
import com.smcapis.smcapis.services.interfaces.CatalogoArticuloService;

@Service
public class CatalogoArticuloServiceImpl implements CatalogoArticuloService {

    private final CatalogoArticuloRepository repository;

    public CatalogoArticuloServiceImpl(CatalogoArticuloRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CatalogoArticuloResponse> obtenerArticulos() {
        return repository.obtenerArticulos();
    }
}
