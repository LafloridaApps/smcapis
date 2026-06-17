package com.smcapis.smcapis.repositories.interfaces;

import com.smcapis.smcapis.dto.FuncionarioDto;
import java.util.List;

public interface FuncionarioListRepository {

    List<FuncionarioDto> getFuncionario(Integer rut);
}
