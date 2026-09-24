package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.ArticuloResponse;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.mapper.ArticuloMapper;
import com.smcapis.smcapis.repositories.interfaces.ConsultaArticuloRepository;
import org.springframework.core.io.Resource;

@Repository
public class ConsultaArticuloImpl implements ConsultaArticuloRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ArticuloMapper articuloMapper;

    private final String sql;

    public ConsultaArticuloImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader, ArticuloMapper articuloMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.articuloMapper = articuloMapper;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:sql/consultaarticulo.sql");

            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public ArticuloResponse obtenerCodigoXcorrelatico(int codinv) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codinv", codinv);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    params,
                    articuloMapper::mapToArticuloResponse);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }


}
