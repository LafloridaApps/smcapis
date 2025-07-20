package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.DetalleFeriado;
import com.smcapis.smcapis.dto.ResumenFeriado;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.expections.RecursoNoEncontradoException;
import com.smcapis.smcapis.repositories.interfaces.FeriadoRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.Collections;
import java.util.List;

@Repository
public class FeriadoRepositoryImpl implements FeriadoRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String sql;
    private final String sqlDetalle;

    public FeriadoRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:resumenferiados.sql");
            Resource resourceDetalle = resourceLoader.getResource("classpath:detalleferiado.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            this.sqlDetalle = new String(resourceDetalle.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<ResumenFeriado> getFeriadoByRutAndIdent(Integer rut, Integer ident) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", ident);
        params.addValue("currentYear", Year.now().getValue());
        params.addValue("currentMonth", LocalDate.now().getMonthValue() - 1);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapToResumenDFeriado);
        } catch (EmptyResultDataAccessException e) {

            return Collections.emptyList();
        }
    }

    private ResumenFeriado mapToResumenDFeriado(ResultSet rs, int rowNum) throws SQLException {

        ResumenFeriado resumenFeriado = new ResumenFeriado();

        resumenFeriado.setAnio(rs.getInt("ano"));
        resumenFeriado.setDiasCorresponden(rs.getInt("corresponde"));
        resumenFeriado.setDiasPendientes(rs.getInt("diaspendientes"));
        resumenFeriado.setDiasTomados(rs.getInt("diastomados"));
        resumenFeriado.setDiasAcumulados(rs.getInt("acumuladoant"));
        resumenFeriado.setTotal(rs.getInt("totaldias"));

        return resumenFeriado;
    }

    @Override
    public List<DetalleFeriado> getDetalleFeriadoByRutAndIdent(Integer rut, Integer ident) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", ident);

        try {
            return namedParameterJdbcTemplate.query(
                    sqlDetalle,
                    params,
                    this::mapToDetalleFeriado);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException(
                    "No se encontraron registros para el rut: " + rut + " y ident: " + ident);
        }
    }

    private DetalleFeriado mapToDetalleFeriado(ResultSet rs, int rowNum) throws SQLException {
        DetalleFeriado detalleAdm = new DetalleFeriado();
        detalleAdm.setNumero(rs.getInt("numero"));
        detalleAdm.setResolucion(rs.getString("resolucion"));
        detalleAdm.setFechaResolucion(rs.getDate("fechaResolucion").toLocalDate());
        detalleAdm.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
        detalleAdm.setFechaTermino(rs.getDate("fechaTermino").toLocalDate());
        detalleAdm.setPeriodo(rs.getDouble("periodo"));
        return detalleAdm;
    }

}
