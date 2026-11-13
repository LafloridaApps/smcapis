package com.smcapis.smcapis.services;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.PropietarioNegocio;
import com.smcapis.smcapis.repositories.interfaces.ApiPropietarioNegocioRepository;
import com.smcapis.smcapis.services.interfaces.ConsultaNegocioService;

@Service 
public class ConsultaNegocioServiceImpl implements ConsultaNegocioService {

    private final ApiPropietarioNegocioRepository apiPropietarioNegocioRepository;

    public ConsultaNegocioServiceImpl(ApiPropietarioNegocioRepository apiPropietarioNegocioRepository) {
        this.apiPropietarioNegocioRepository = apiPropietarioNegocioRepository;
    }

    @Override
    public PropietarioNegocio obtenerNegocios(Integer rut) {
        return apiPropietarioNegocioRepository.obtenerPropietarioNegocio(rut);
    }

}
