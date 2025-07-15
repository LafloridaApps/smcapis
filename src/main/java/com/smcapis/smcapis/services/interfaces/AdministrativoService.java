package com.smcapis.smcapis.services.interfaces;

import com.smcapis.smcapis.dto.ResumenAdm;

public interface AdministrativoService {

    ResumenAdm getAdminByRutAndIdent(Integer rut, Integer ident);

}
