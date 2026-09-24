package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;
import com.smcapis.smcapis.dto.CatalogoArticuloResponse;

public interface CatalogoArticuloRepository {
    List<CatalogoArticuloResponse> obtenerArticulos();
}
