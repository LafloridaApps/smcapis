package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.AusenciasResponse;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.AusenciaRespository;

@Repository
public class AusenciasRepositoryImpl implements AusenciaRespository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String sql;

    public AusenciasRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:buscaausencias.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<AusenciasResponse> getAusenciasByRutAndIdent(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", ident);
        params.addValue("fechaInicio", fechaInicio);
        params.addValue("fechaFin", fechaFin);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapToAusencia);
        } catch (EmptyResultDataAccessException e) {

            return Collections.emptyList();
        }
    }

    private AusenciasResponse mapToAusencia(ResultSet rs, int rowNum) throws SQLException {

        AusenciasResponse ausencia = new AusenciasResponse();
        ausencia.setRut(rs.getInt("rut"));
        ausencia.setIdent(rs.getInt("ident"));
        ausencia.setTipoAusencia(rs.getString("desctipoausencia"));
        ausencia.setFechaInicio(rs.getString("fechainicio"));
        ausencia.setFechaTermino(rs.getString("fechatermino"));

        return ausencia;

    }

    

}
