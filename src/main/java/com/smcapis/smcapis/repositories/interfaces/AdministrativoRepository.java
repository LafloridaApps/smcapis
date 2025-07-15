package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.DetalleAdm;
import com.smcapis.smcapis.dto.ResumenAdm;

public interface AdministrativoRepository {

    ResumenAdm getResumenAdm(Integer rut, Integer ident);

    List<DetalleAdm> getDetalleAdmByRutAndIdent(Integer rut, Integer ident);

}
