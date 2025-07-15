package com.smcapis.smcapis.services.interfaces;


import com.smcapis.smcapis.dto.ResumenFeriado;

public interface FeriadosService {

    ResumenFeriado getFeriadoByRutAndIdent(Integer rut, Integer ident);

}
