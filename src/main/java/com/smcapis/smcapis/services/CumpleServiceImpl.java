package com.smcapis.smcapis.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.FuncionarioDto;

import com.smcapis.smcapis.repositories.interfaces.CumpleRepository;

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
                .filter(f -> f.getFechaNacimiento().getMonthValue() == LocalDate.now().getMonthValue() &&
                        f.getFechaNacimiento().getDayOfMonth() >= LocalDate.now().getDayOfMonth())
                .sorted((f1, f2) -> f1.getFechaNacimiento().compareTo(f2.getFechaNacimiento()))
                .toList();
    }

}
