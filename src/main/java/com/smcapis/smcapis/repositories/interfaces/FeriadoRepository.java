package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.DetalleFeriado;
import com.smcapis.smcapis.dto.ResumenFeriado;

public interface FeriadoRepository {

    List<ResumenFeriado> getFeriadoByRutAndIdent(Integer rut, Integer ident);

    List<DetalleFeriado> getDetalleFeriadoByRutAndIdent(Integer rut, Integer ident);

}
