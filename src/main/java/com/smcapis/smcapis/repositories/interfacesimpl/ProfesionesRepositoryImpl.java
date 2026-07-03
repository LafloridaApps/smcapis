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
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.ProfesionDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.ProfesionesRepository;
import org.springframework.core.io.Resource;


@Repository
public class ProfesionesRepositoryImpl implements ProfesionesRepository{

       private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sql;

    public ProfesionesRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:sql/profesiones.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<ProfesionDto> getProfesionFuncionario(Integer rut) {
         MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapDto);
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<>();
        }
    }

    private ProfesionDto mapDto(ResultSet rs, int rowNum) throws SQLException {

        return new ProfesionDto.Builder()
                .profesion(rs.getString("profesion"))
                .build();

    }

}
