package com.smcapis.smcapis.services;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.DetalleAdm;
import com.smcapis.smcapis.dto.ResumenAdm;
import com.smcapis.smcapis.repositories.interfaces.AdministrativoRepository;
import com.smcapis.smcapis.services.interfaces.AdministrativoService;
import java.util.ArrayList;

@Service
public class AdministrativoServiceImpl implements AdministrativoService {

    private final AdministrativoRepository administrativoRepository;

    public AdministrativoServiceImpl(AdministrativoRepository administrativoRepository) {
        this.administrativoRepository = administrativoRepository;
    }

    @Override
    public ResumenAdm getAdminByRutAndIdent(Integer rut, Integer ident) {

        ResumenAdm resumenAdm = validateObject(administrativoRepository.getResumenAdm(rut, ident));

        resumenAdm.setDetalle(getDetalleAdm(rut, ident));

        return resumenAdm;

    }

    private List<DetalleAdm> getDetalleAdm(Integer rut, Integer ident) {

        return administrativoRepository.getDetalleAdmByRutAndIdent(rut, ident);
    }

    private ResumenAdm validateObject(ResumenAdm resumenAdm) {
        if (resumenAdm == null) {
            resumenAdm = new ResumenAdm();
            resumenAdm.setMaximo(6D);
            resumenAdm.setUsados(0D);
            resumenAdm.setSaldo(6D);
            resumenAdm.setAnio(Year.now().getValue());
            resumenAdm.setDetalle(new ArrayList<>());
            return resumenAdm;
        } else {
            return resumenAdm;

        }
    }

}
