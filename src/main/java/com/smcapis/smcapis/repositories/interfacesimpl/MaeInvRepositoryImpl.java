package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.ArticuloResponse;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.MaeInvRepository;
import org.springframework.core.io.Resource;
import com.smcapis.smcapis.utiles.UtilsHelper;
import com.smcapis.smcapis.mapper.ArticuloMapper;

@Repository
public class MaeInvRepositoryImpl implements MaeInvRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ArticuloMapper articuloMapper;

    private final String sql;

    public MaeInvRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader, ArticuloMapper articuloMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.articuloMapper = articuloMapper;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:maeinv.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public ArticuloResponse getArticuloByCodigoInv(String codart, Integer correlativo) {
        Map<String, String> valores = UtilsHelper.obtieneCodigoInv(codart);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codgrupoart", valores.get("codgrupo"));
        params.addValue("codsubgrupoart", valores.get("codsubgrupo"));
        params.addValue("codrubroart", valores.get("codrubro"));
        params.addValue("codart", valores.get("codigo"));
        params.addValue("codinv", correlativo);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    params,
                    articuloMapper::mapToArticuloResponse);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

}
