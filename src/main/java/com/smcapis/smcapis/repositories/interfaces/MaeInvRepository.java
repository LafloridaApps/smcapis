package com.smcapis.smcapis.repositories.interfaces;

import com.smcapis.smcapis.dto.ArticuloResponse;

public interface MaeInvRepository {

    ArticuloResponse getArticuloByCodigoInv(String codart, Integer codinv);

}
