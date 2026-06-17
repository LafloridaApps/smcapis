package com.smcapis.smcapis.services.interfaces;

import com.smcapis.smcapis.dto.FuncionarioDto;
import java.util.List;

public interface FuncionarioListService {

    List<FuncionarioDto> getFuncionario(Integer rut);


}
