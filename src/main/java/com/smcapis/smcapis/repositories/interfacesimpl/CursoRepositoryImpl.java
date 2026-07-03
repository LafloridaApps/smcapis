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

import com.smcapis.smcapis.dto.CursoFuncionarioDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.CursoRepository;
import org.springframework.core.io.Resource;

@Repository
public class CursoRepositoryImpl implements CursoRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sql;

    public CursoRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:sql/cursos.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<CursoFuncionarioDto> getCursoByRut(Integer rut, Integer ident) {
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

    private CursoFuncionarioDto mapDto(ResultSet rs, int rowNum) throws SQLException {

        return new CursoFuncionarioDto.Builder()
                .fechaInicioCurso(rs.getDate("fechainiciocurso").toLocalDate())
                .fechaTerminoCurso(rs.getDate("fechaterminocurso").toLocalDate())
                .descripcionTipoCurso(rs.getString("desctipocurso"))
                .institucion(rs.getString("descinsteducacion"))
                .nivelTecnico(rs.getString("nombrenivtec"))
                .descripcionCurso(rs.getString("desccurso"))
                .horas(rs.getInt("cursohoras"))
                .nota(rs.getDouble("notacurso"))
                .materiaCurso(rs.getString("materiacurso"))
                .build();

    }

}
