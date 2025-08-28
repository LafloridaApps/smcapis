package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.DetalleFeriadoLegal;
import com.smcapis.smcapis.dto.ResumenFeriadoLegal;

public interface FeriadoRepository {

    List<ResumenFeriadoLegal> getFeriadoByRutAndIdent(Integer rut, Integer ident);

    List<DetalleFeriadoLegal> getDetalleFeriadoByRutAndIdent(Integer rut, Integer ident);

}
