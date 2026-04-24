package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.OficinaInvResponse;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.OficinaInventarioRepository;
import org.springframework.core.io.Resource;

@Service
public class OficinaInventarioRepositoryImpl implements OficinaInventarioRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String sql;

    public OficinaInventarioRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:oficinasinv.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<OficinaInvResponse> getOficinasByDepto(String depto) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("depto", depto);

        try {

            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapToDto);

        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    private OficinaInvResponse mapToDto(ResultSet rs, int row) throws SQLException {
        return new OficinaInvResponse(
                rs.getString("depto"),
                rs.getInt("linoficina"),
                rs.getString("nombreoficina"),
                rs.getString("responsableoficina"),
                rs.getString("cargooficina")

        );
    }

}
