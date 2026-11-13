package com.smcapis.smcapis.services.interfaces;

import com.smcapis.smcapis.dto.FuncionarioContratoResponse;
import java.util.List;

public interface FuncionarioListService {

    List<FuncionarioContratoResponse> getFuncionario(Integer rut);


}
