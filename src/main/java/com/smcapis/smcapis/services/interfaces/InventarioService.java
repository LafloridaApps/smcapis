package com.smcapis.smcapis.services.interfaces;

import com.smcapis.smcapis.dto.ArticuloResponse;

public interface InventarioService {

    ArticuloResponse getArticuloByCodigo(String codigo, Integer correlativo);

}
