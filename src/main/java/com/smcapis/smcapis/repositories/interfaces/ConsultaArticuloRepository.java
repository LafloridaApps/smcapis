package com.smcapis.smcapis.repositories.interfaces;

import com.smcapis.smcapis.dto.ArticuloResponse;

public interface ConsultaArticuloRepository {

    ArticuloResponse obtenerCodigoXcorrelatico(int codinv);

}
