package com.smcapis.smcapis.services.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.ArticuloResponse;
import com.smcapis.smcapis.dto.OficinaInvResponse;

public interface InventarioService {

    ArticuloResponse getArticuloByCodigo(String codigo, Integer correlativo);

    List<ArticuloResponse> getArticuloByDepto(String depto, Integer linoficina);

    List<OficinaInvResponse> getOficinasByCodigoDepto(String depto);

}
