package com.smcapis.smcapis.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.AusenciasLicenciasFuncioanarioDto;
import com.smcapis.smcapis.repositories.interfaces.LicenciasEntreFechasRepository;
import com.smcapis.smcapis.services.interfaces.LicenciasEntreFechasService;

@Service
public class LicenciasEntreFechasServiceImpl implements LicenciasEntreFechasService {

    private final LicenciasEntreFechasRepository licenciasEntreFechasRepository;

    public LicenciasEntreFechasServiceImpl(LicenciasEntreFechasRepository licenciasEntreFechasRepository) {
        this.licenciasEntreFechasRepository = licenciasEntreFechasRepository;
    }

    @Override
    public List<AusenciasLicenciasFuncioanarioDto> getLicenciasMedicasByDeptos(List<String> deptos, LocalDate fechaIni, LocalDate fechaFin) {
        return licenciasEntreFechasRepository.obtenerLicenciasMedicasByDepto(deptos, fechaIni, fechaFin);
    }

}
