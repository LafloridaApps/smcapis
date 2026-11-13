package com.smcapis.smcapis.repositories.interfaces;

import com.smcapis.smcapis.dto.FuncionarioContratoResponse;
import java.util.List;

public interface FuncionarioListRepository {

    List<FuncionarioContratoResponse> getFuncionario(Integer rut);
}
