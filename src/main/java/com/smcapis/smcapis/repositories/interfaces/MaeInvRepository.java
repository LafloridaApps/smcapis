package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.ArticuloResponse;

public interface MaeInvRepository {

    ArticuloResponse getArticuloByCodigoInv(String codart, Integer codinv);

    List<ArticuloResponse> getArticuloByDepto(String depto, Integer linoficina);

}
