package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.FuncionarioDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.FuncionarioRespository;
import com.smcapis.smcapis.utiles.FotoUtils;

@Repository
public class FuncionarioRepositoryImpl implements FuncionarioRespository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String sql;

    public FuncionarioRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {
            Resource resource = resourceLoader.getResource("classpath:sql/funcionario.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public FuncionarioDto getFuncionario(Integer rut) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    params,
                    this::mapToFuncionarioDto);
        } catch (EmptyResultDataAccessException e) {

            throw new EmptyResultDataAccessException("No se encontró funcionario con rut: " + rut, 1);
        }

    }

    private FuncionarioDto mapToFuncionarioDto(ResultSet rs, int row) throws SQLException {

        byte[] imageBytes = rs.getBytes("foto");
        LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento") != null ? rs.getDate("fecha_nacimiento").toLocalDate() : null;

        return FuncionarioDto.builder()
                .rut(rs.getInt("rut"))
                .vrut(rs.getString("vrut"))
                .paterno(rs.getString("apellidopaterno"))
                .materno(rs.getString("apellidomaterno"))
                .email(rs.getString("email"))
                .foto(imageBytes != null ? FotoUtils.fotoConverter(imageBytes) : " ")
                .nombres(rs.getString("nombres"))
                .departamento(rs.getString("departamento"))
                .ident(rs.getInt("ident"))
                .fechaNacimiento(fechaNacimiento)
                .tipoContrato(rs.getString("tipocontrato"))
                .escalafon(rs.getString("nombreescalafon"))
                .grado(rs.getInt("grado"))
                .codDeptoExt(rs.getString("coddepto"))
                .build();

    }

}
