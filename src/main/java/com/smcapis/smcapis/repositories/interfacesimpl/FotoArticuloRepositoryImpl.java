package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.FotoArticuloRepository;
import com.smcapis.smcapis.utiles.UtilsHelper;

import org.springframework.core.io.Resource;

@Repository
public class FotoArticuloRepositoryImpl implements FotoArticuloRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sql;

    public FotoArticuloRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:fotoarticuloinv.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public String fotoArticulo(String codigo, Integer correlativo) {
        Map<String, String> valores = UtilsHelper.obtieneCodigoInv(codigo);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codgrupoart", valores.get("codgrupo"));
        params.addValue("codsubgrupoart", valores.get("codsubgrupo"));
        params.addValue("codrubroart", valores.get("codrubro"));
        params.addValue("codart", valores.get("codigo"));
        params.addValue("codinv", correlativo);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    params,
                    this::mapToString);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    private String mapToString(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("fotoarticulo");
    }

}
