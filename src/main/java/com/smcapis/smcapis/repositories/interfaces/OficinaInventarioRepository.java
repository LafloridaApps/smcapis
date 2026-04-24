package com.smcapis.smcapis.repositories.interfaces;

import java.util.List;

import com.smcapis.smcapis.dto.OficinaInvResponse;

public interface OficinaInventarioRepository {

    List<OficinaInvResponse> getOficinasByDepto(String depto);

}
