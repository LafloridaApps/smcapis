package com.smcapis.smcapis.services;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.ArticuloResponse;
import com.smcapis.smcapis.repositories.interfaces.MaeInvRepository;
import com.smcapis.smcapis.services.interfaces.InventarioService;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final MaeInvRepository maeInvRepository;

    public InventarioServiceImpl(MaeInvRepository maeInvRepository) {
        this.maeInvRepository = maeInvRepository;
    }

    @Override
    public ArticuloResponse getArticuloByCodigo(String codigo, Integer correlativo) {
        return maeInvRepository.getArticuloByCodigoInv(codigo, correlativo);
    }

}
