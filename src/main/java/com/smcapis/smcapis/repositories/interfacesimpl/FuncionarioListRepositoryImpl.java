package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.FuncionarioDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.FuncionarioListRepository;
import com.smcapis.smcapis.utiles.FechaUtils;
import com.smcapis.smcapis.utiles.FotoUtils;

import org.springframework.core.io.ResourceLoader;

@Repository
public class FuncionarioListRepositoryImpl implements FuncionarioListRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String sql;

    public FuncionarioListRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {
            Resource resource = resourceLoader.getResource("classpath:funcionarioList.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<FuncionarioDto> getFuncionario(Integer rut) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);

        List<FuncionarioDto> funcionarios = namedParameterJdbcTemplate.query(sql,
                params,
                this::mapToFuncionarioDto);

        if (funcionarios.isEmpty()) {
            throw new EmptyResultDataAccessException("No se encontró funcionario con rut: " + rut, 1);
        }

        return funcionarios;

    }

    private FuncionarioDto mapToFuncionarioDto(ResultSet rs, int row) throws SQLException {

        byte[] imageBytes = rs.getBytes("foto");
        LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento") != null ? rs.getDate("fecha_nacimiento").toLocalDate() : null;
        LocalDate fechaFin = rs.getDate("fechafin") != null ? rs.getDate("fechafin").toLocalDate() : null;


        return FuncionarioDto.builder()
        .rut(rs.getInt("rut"))
        .vrut(rs.getString("vrut"))
        .email(rs.getString("email"))
        .paterno(rs.getString("apellidopaterno"))
        .materno(rs.getString("apellidomaterno"))
        .nombres(rs.getString("nombres"))
        .codDeptoExt(rs.getString("coddepto"))
        .foto(imageBytes != null ? FotoUtils.fotoConverter(imageBytes) : " ")
        .departamento(rs.getString("departamento"))
        .ident(rs.getInt("ident"))
        .tipoContrato(rs.getString("tipocontrato"))
        .escalafon(rs.getString("nombreescalafon"))
        .grado(rs.getInt("grado"))
        .fechafin(fechaFin)
        .vigente(estadoContrato(fechaFin))
        .fechaNacimiento(fechaNacimiento)

        .build();


    }

    private boolean estadoContrato(LocalDate fechaContrato) {
        return fechaContrato == null || fechaContrato.isAfter(FechaUtils.fechaActual());
    }

}
