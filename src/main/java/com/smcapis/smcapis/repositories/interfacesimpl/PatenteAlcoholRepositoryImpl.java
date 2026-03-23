package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.PatentesAlcoholDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.PatenteAlcoholRepository;
import org.springframework.core.io.Resource;

@Repository
public class PatenteAlcoholRepositoryImpl implements PatenteAlcoholRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final String sql;

    public PatenteAlcoholRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate,
            ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;

        try {
            Resource resource = resourceLoader.getResource("classpath:patcomalchol.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<PatentesAlcoholDto> getPatentesAlcoholByRut(Integer rut) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        try {
            return jdbcTemplate.query(sql,params,
                    this::mapToDto);
        } catch (EmptyResultDataAccessException e) {

            throw new EmptyResultDataAccessException("No se encontraron patentes de alcojol", 1);
        }
    }

    private PatentesAlcoholDto mapToDto(ResultSet rs, int row) throws SQLException {

        return new PatentesAlcoholDto(rs.getInt("clave"),
                rs.getInt("rol"),
                rs.getInt("rut"),
                rs.getString("descripcalle"),
                rs.getInt("dirnumero"),
                rs.getString("diraclara"),
                rs.getString("rol_sii"),
                rs.getString("vrol_sii"));

    }
}
