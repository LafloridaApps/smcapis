package com.smcapis.smcapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.ArticuloResponse;
import com.smcapis.smcapis.dto.OficinaInvResponse;
import com.smcapis.smcapis.repositories.interfaces.MaeInvRepository;
import com.smcapis.smcapis.repositories.interfaces.OficinaInventarioRepository;
import com.smcapis.smcapis.services.interfaces.InventarioService;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final MaeInvRepository maeInvRepository;
    private final OficinaInventarioRepository oficinaInventarioRepository;

    public InventarioServiceImpl(MaeInvRepository maeInvRepository,
            OficinaInventarioRepository oficinaInventarioRepository) {
        this.maeInvRepository = maeInvRepository;
        this.oficinaInventarioRepository = oficinaInventarioRepository;
    }

    @Override
    public ArticuloResponse getArticuloByCodigo(String codigo, Integer correlativo) {
        return maeInvRepository.getArticuloByCodigoInv(codigo, correlativo);
    }

    @Override
    public List<ArticuloResponse> getArticuloByDepto(String depto, Integer linoficina) {
        return maeInvRepository.getArticuloByDepto(depto, linoficina);
    }

    @Override
    public List<OficinaInvResponse> getOficinasByCodigoDepto(String depto) {
        return oficinaInventarioRepository.getOficinasByDepto(depto);
    }

}
