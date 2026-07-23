package com.smcapis.smcapis.repositories.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.smcapis.smcapis.dto.AusenciasLicenciasFuncioanarioDto;

public interface LicenciasEntreFechasRepository {

    List<AusenciasLicenciasFuncioanarioDto> obtenerLicenciasMedicasByDepto(List<String> deptos, LocalDate fechaIni, LocalDate fechaFin);

}
