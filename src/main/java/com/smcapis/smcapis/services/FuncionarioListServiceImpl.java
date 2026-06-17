package com.smcapis.smcapis.services;

import com.smcapis.smcapis.dto.FuncionarioDto;
import com.smcapis.smcapis.repositories.interfaces.FuncionarioListRepository;
import com.smcapis.smcapis.services.interfaces.FuncionarioListService;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FuncionarioListServiceImpl implements FuncionarioListService {

    private final FuncionarioListRepository funcionarioListRepository;

    public FuncionarioListServiceImpl(FuncionarioListRepository funcionarioListRepository) {
        this.funcionarioListRepository = funcionarioListRepository;
    }

    @Override
    public List<FuncionarioDto> getFuncionario(Integer rut) {
        return funcionarioListRepository.getFuncionario(rut);
    }

}
