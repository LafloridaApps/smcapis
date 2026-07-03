package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.CargasFamiliaresDto;

public interface CargasFamiliaresRepository {

    List<CargasFamiliaresDto> getCargasFamiliaresByRut(Integer rut, Integer ident);

}
