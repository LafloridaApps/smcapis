package com.smcapis.smcapis.services.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.ArticuloResponse;

public interface InventarioService {

    ArticuloResponse getArticuloByCodigo(String codigo, Integer correlativo);

    List<ArticuloResponse> getArticuloByDepto(String depto);

}
