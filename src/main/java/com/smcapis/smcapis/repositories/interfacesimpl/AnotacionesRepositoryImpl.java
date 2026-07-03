package com.smcapis.smcapis.repositories.interfacesimpl;

import com.smcapis.smcapis.dto.AnotacionesFuncionarioDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.AnotacionesRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.Resource;

import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AnotacionesRepositoryImpl implements AnotacionesRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sql;

    public AnotacionesRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:sql/anotaciones.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<AnotacionesFuncionarioDto> getAnotacionesByRutAndIdent(Integer rut, Integer ident) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", ident);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapDto);
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<>();
        }
    }

    private AnotacionesFuncionarioDto mapDto(ResultSet rs, int rowNum) throws SQLException {

        return new AnotacionesFuncionarioDto.Builder()
                .fechaAnotacion(rs.getDate("fechaanotacion").toLocalDate())
                .glosaAnotacion(rs.getString("glosaanotacion"))
                .anoCalif(rs.getInt("anocalif"))
                .descripsubgrupocalif(rs.getString("descripsubgrupocalif"))
                .desctipoanotacion(rs.getString("desctipoanotacion"))
                .build();

    }

}
