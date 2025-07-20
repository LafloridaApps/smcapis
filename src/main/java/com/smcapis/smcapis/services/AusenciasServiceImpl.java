package com.smcapis.smcapis.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.AusenciasResponse;
import com.smcapis.smcapis.repositories.interfaces.AusenciaRespository;
import com.smcapis.smcapis.services.interfaces.AusenciasService;

@Service
public class AusenciasServiceImpl implements AusenciasService {

    private final AusenciaRespository ausenciaRespository;


    public AusenciasServiceImpl(AusenciaRespository ausenciaRespository
            ) {
        this.ausenciaRespository = ausenciaRespository;
    }

    @Override
    public List<AusenciasResponse> getAusenciaBetweenDatesByRutAndIdent(Integer rut, Integer ident,
            LocalDate fechaInicio, LocalDate fechaFin) {

        
        return ausenciaRespository.getAusenciasByRutAndIdent(rut, ident, fechaInicio,
                fechaFin);


    }

}
