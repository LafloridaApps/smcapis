package com.smcapis.smcapis.services.interfaces;

import java.util.List;
import com.smcapis.smcapis.dto.CatalogoArticuloResponse;

public interface CatalogoArticuloService {
    List<CatalogoArticuloResponse> obtenerArticulos();
}
