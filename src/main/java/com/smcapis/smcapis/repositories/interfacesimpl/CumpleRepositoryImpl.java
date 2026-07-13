package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.FuncionarioDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.CumpleRepository;
import com.smcapis.smcapis.utiles.FechaUtils;
import com.smcapis.smcapis.utiles.FotoUtils;

import org.springframework.core.io.Resource;

@Repository
public class CumpleRepositoryImpl implements CumpleRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sql;

    public CumpleRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:sql/cumple.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<FuncionarioDto> obtenerCumpleMes() {

        List<FuncionarioDto> funcionarios = namedParameterJdbcTemplate.query(sql,

                this::mapToFuncionarioDto);

        if (funcionarios.isEmpty()) {
           return new ArrayList<>();
        }

        return funcionarios;

    }

    private FuncionarioDto mapToFuncionarioDto(ResultSet rs, int row) throws SQLException {

        byte[] imageBytes = rs.getBytes("foto");

        Optional<LocalDate> fechaNacimiento = Optional.ofNullable(rs.getDate("fecha_nacimiento"))
                .map(java.sql.Date::toLocalDate);

        LocalDate fechaFin = Optional.ofNullable(rs.getDate("FECHAFIN"))
                .map(java.sql.Date::toLocalDate)
                .orElse(null);

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
                .fechaNacimiento(fechaNacimiento.orElse(null))
                .tipoContrato(rs.getString("tipocontrato"))
                .escalafon(rs.getString("nombreescalafon"))
                .grado(rs.getInt("grado"))
                .fechafinContrato(fechaFin)
                .vigente(estadoContrato(fechaFin))
                .build();

    }

    private boolean estadoContrato(LocalDate fechaContrato) {
        return fechaContrato == null || !fechaContrato.isBefore(FechaUtils.fechaActual());
    }

}
