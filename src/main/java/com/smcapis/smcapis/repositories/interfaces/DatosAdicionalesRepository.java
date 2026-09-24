package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.ArticuloDatosAdicionales;

public interface DatosAdicionalesRepository {

    List<ArticuloDatosAdicionales> getAdicionalesByCodigo(String codigo, Integer correlativo);

    List<ArticuloDatosAdicionales> getAdicionalesByCodInv(Integer correlativo);

}
