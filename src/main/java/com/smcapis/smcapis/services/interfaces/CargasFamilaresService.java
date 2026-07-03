package com.smcapis.smcapis.services.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.CargasFamiliaresDto;

public interface CargasFamilaresService {

    List<CargasFamiliaresDto> getCargasFamiliaresByRut(Integer rut, Integer ident);

}
