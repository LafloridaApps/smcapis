package com.smcapis.smcapis.services;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.DetalleFeriadoLegal;
import com.smcapis.smcapis.dto.ResumenFeriadoLegal;
import com.smcapis.smcapis.repositories.interfaces.FeriadoRepository;
import com.smcapis.smcapis.services.interfaces.FeriadosService;

@Service
public class FeriadoServiceImpl implements FeriadosService {

    private final FeriadoRepository feriadoRepository;

    public FeriadoServiceImpl(FeriadoRepository feriadoRepository) {
        this.feriadoRepository = feriadoRepository;
    }

    @Override
    public ResumenFeriadoLegal getFeriadoByRutAndIdent(Integer rut, Integer ident) {

        ResumenFeriadoLegal resumenFeriado = feriadoRepository.getFeriadoByRutAndIdent(rut, ident)
                .stream()
                .filter(r -> r.getAnio() == Year.now().getValue())
                .findFirst()
                .orElse( new ResumenFeriadoLegal());

        resumenFeriado.setDetalle(getDetalleFeriadoByRutAndIdent(rut, ident));

        return resumenFeriado;

    }

    private List<DetalleFeriadoLegal> getDetalleFeriadoByRutAndIdent(Integer rut, Integer ident) {

        return feriadoRepository.getDetalleFeriadoByRutAndIdent(rut, ident);

    }

    

}
