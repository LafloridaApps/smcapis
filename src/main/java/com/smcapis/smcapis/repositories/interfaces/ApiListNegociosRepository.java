package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.DetalleNegocio;

public interface ApiListNegociosRepository {

     List<DetalleNegocio> obtenerListaNegociosRut(Integer rut);

}
