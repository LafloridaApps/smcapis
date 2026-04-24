package com.smcapis.smcapis.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.AsistenciaDto;
import com.smcapis.smcapis.repositories.interfaces.AsistenciaRepository;
import com.smcapis.smcapis.services.interfaces.AsistenciaService;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaServiceImpl(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    public List<AsistenciaDto> getAsistenciaByRutAndIdent(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin) {
        return asistenciaRepository.getAsistenciaByRutAndIdent(rut, ident, fechaInicio, fechaFin);
    }

}
