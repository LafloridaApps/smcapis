package com.smcapis.smcapis.services;

import java.util.List;
import org.springframework.stereotype.Service;
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

        // 1. Obtenemos toda la lista que viene del repositorio
        List<ResumenFeriadoLegal> listaResumenes = feriadoRepository.getFeriadoByRutAndIdent(rut, ident);

        // 2. Buscamos el año 2026 específicamente (o el primero que encuentres si
        // prefieres)
        ResumenFeriadoLegal resumenFeriado = listaResumenes.stream()
                .filter(r -> r.getAnio() != null && r.getAnio().equals(2026))
                .findFirst()
                .orElseGet(() -> !listaResumenes.isEmpty() ? listaResumenes.get(0) : new ResumenFeriadoLegal());

        // 3. Le inyectamos la lista de detalles al objeto de resumen único
        resumenFeriado.setDetalle(feriadoRepository.getDetalleFeriadoByRutAndIdent(rut, ident));

        return resumenFeriado;
    }
}