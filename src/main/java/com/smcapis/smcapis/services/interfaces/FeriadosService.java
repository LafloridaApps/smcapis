package com.smcapis.smcapis.services.interfaces;


import com.smcapis.smcapis.dto.ResumenFeriadoLegal;

public interface FeriadosService {

    ResumenFeriadoLegal getFeriadoByRutAndIdent(Integer rut, Integer ident);

}
