package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.smcapis.smcapis.dto.ArticuloDatosAdicionales;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.DatosAdicionalesService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.utiles.UtilsHelper;

@Repository
public class DatosAdicionalesServiceImpl implements DatosAdicionalesService {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String sql;

    public DatosAdicionalesServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {
            Resource resource = resourceLoader.getResource("classpath:datosadicionalesarticulo.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<ArticuloDatosAdicionales> getAdicionalesByCodigo(String codigo, Integer correlativo) {
        Map<String, String> valores = UtilsHelper.obtieneCodigoInv(codigo);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codgrupoart", valores.get("codgrupo"));
        params.addValue("codsubgrupoart", valores.get("codsubgrupo"));
        params.addValue("codrubroart", valores.get("codrubro"));
        params.addValue("codart", valores.get("codigo"));
        params.addValue("codinv", correlativo);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapToDto);
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<>();
        }
    }

    private ArticuloDatosAdicionales mapToDto(ResultSet rs, int rowNum) throws SQLException {

        return new ArticuloDatosAdicionales(
                rs.getInt("codadic"),
                rs.getString("descadic"),
                rs.getString("nombreadic")

        );

    }

}
