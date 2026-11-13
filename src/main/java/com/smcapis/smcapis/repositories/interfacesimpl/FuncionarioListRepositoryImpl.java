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

import com.smcapis.smcapis.dto.FuncionarioContratoResponse;
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
            Resource resource = resourceLoader.getResource("classpath:sql/funcionarioList.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<FuncionarioContratoResponse> getFuncionario(Integer rut) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);

        List<FuncionarioContratoResponse> funcionarios = namedParameterJdbcTemplate.query(sql,
                params,
                this::mapToFuncionarioContratoResponse);

        if (funcionarios.isEmpty()) {
            throw new EmptyResultDataAccessException("No se encontró funcionario con rut: " + rut, 1);
        }

        return funcionarios;

    }

    private FuncionarioContratoResponse mapToFuncionarioContratoResponse(ResultSet rs, int row) throws SQLException {

        byte[] imageBytes = rs.getBytes("foto");
        LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento") != null ? rs.getDate("fecha_nacimiento").toLocalDate() : null;
        LocalDate fechaInicio = rs.getDate("fechaini") != null ? rs.getDate("fechaini").toLocalDate() : null;
        LocalDate fechaFin = rs.getDate("fechafin") != null ? rs.getDate("fechafin").toLocalDate() : null;


        return new FuncionarioContratoResponse(
                rs.getInt("rut"), rs.getString("vrut"), rs.getString("apellidopaterno"),
                rs.getString("apellidomaterno"), rs.getString("email"), rs.getString("coddepto"),
                rs.getString("nombres"), imageBytes != null ? FotoUtils.fotoConverter(imageBytes) : " ",
                rs.getString("departamento"), rs.getInt("ident"), rs.getString("tipocontrato"),
                rs.getString("nombreescalafon"), rs.getInt("grado"), fechaInicio, fechaFin,
                estadoContrato(fechaFin), fechaNacimiento,
                rs.getString("numresolcontr"), rs.getString("obs_adicional_cont"));


    }

    private boolean estadoContrato(LocalDate fechaContrato) {
        return fechaContrato == null || !fechaContrato.isBefore(FechaUtils.fechaActual());
    }

}
