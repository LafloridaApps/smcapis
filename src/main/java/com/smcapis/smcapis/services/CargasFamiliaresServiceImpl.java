package com.smcapis.smcapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.CargasFamiliaresDto;
import com.smcapis.smcapis.repositories.interfaces.CargasFamiliaresRepository;
import com.smcapis.smcapis.services.interfaces.CargasFamilaresService;

@Service
public class CargasFamiliaresServiceImpl implements CargasFamilaresService {

    private final CargasFamiliaresRepository cargasFamiliaresRepository;

    public CargasFamiliaresServiceImpl(CargasFamiliaresRepository cargasFamiliaresRepository) {
        this.cargasFamiliaresRepository = cargasFamiliaresRepository;
    }

    @Override
    public List<CargasFamiliaresDto> getCargasFamiliaresByRut(Integer rut, Integer ident) {
        return cargasFamiliaresRepository.getCargasFamiliaresByRut(rut, ident);
    }

}
