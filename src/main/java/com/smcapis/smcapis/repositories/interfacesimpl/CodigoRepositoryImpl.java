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

import com.smcapis.smcapis.dto.CodigoResponse;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.CodigoRepository;
import com.smcapis.smcapis.utiles.UtilsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

@Repository
public class CodigoRepositoryImpl implements CodigoRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String sql;
    private static final Logger logger = LoggerFactory.getLogger(CodigoRepositoryImpl.class);

    public CodigoRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {
            Resource resource = resourceLoader.getResource("classpath:sql/codigoart.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public CodigoResponse getCodigo(String codigo) {
        Map<String, String> valores = UtilsHelper.obtieneCodigoInv(codigo);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codgrupoart", valores.get("codgrupo"));
        params.addValue("codsubgrupoart", valores.get("codsubgrupo"));
        params.addValue("codrubroart", valores.get("codrubro"));
        params.addValue("codart", valores.get("codigo"));

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    params,
                    this::mapToDto);

        } catch (EmptyResultDataAccessException e) {
            return null; // Es el comportamiento esperado si no se encuentran registros
        } catch (Exception e) {
            logger.error("Error al buscar código {}", codigo, e);
            return null;
        }
    }

    private CodigoResponse mapToDto(ResultSet rs, int rowNum) throws SQLException {

        return new CodigoResponse(
                rs.getString("codgrupoart"),
                rs.getString("codsubgrupoart"),
                rs.getString("codrubroart"),
                rs.getString("codart"),
                rs.getString("nombreart")

        );

    }

}
