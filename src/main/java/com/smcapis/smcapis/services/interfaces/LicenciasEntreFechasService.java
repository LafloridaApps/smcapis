package com.smcapis.smcapis.services.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.smcapis.smcapis.dto.AusenciasLicenciasFuncioanarioDto;

public interface LicenciasEntreFechasService {

    List<AusenciasLicenciasFuncioanarioDto> getLicenciasMedicasByDeptos(List<String> deptos, LocalDate fechaIni, LocalDate fechaFin);

}
