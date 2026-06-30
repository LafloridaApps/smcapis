package com.smcapis.smcapis.services;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.FuncionarioDto;

import com.smcapis.smcapis.repositories.interfaces.CumpleRepository;
import com.smcapis.smcapis.utiles.FechaUtils;

@Service
public class CumpleServiceImpl implements CumpleService {

    private final CumpleRepository cumpleRepository;

    public CumpleServiceImpl(CumpleRepository cumpleRepository) {
        this.cumpleRepository = cumpleRepository;
    }

    @Override
    public List<FuncionarioDto> obtenerCumpleMes() {
        return cumpleRepository.obtenerCumpleMes()
                .stream()
                .filter(f -> f.getFechaNacimiento() != null)
                .filter(f -> f.getFechaNacimiento().getMonthValue() == FechaUtils.fechaActual().getMonthValue())
                .filter(f -> f.isVigente())
                .sorted(Comparator.comparing(FuncionarioDto::getFechaNacimiento))
                .toList();
    }

}
