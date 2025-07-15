package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smcapis.smcapis.dto.DetalleAdm;
import com.smcapis.smcapis.dto.ResumenAdm;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.expections.RecursoNoEncontradoException;
import com.smcapis.smcapis.repositories.interfaces.AdministrativoRepository;

@Repository
public class AdministrativoRepositoryImpl implements AdministrativoRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String sql;
    private final String sqlDetalle;

    public AdministrativoRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {
            this.sql = new String(Files.readAllBytes(Paths.get("src/main/resources/resumenadm.sql")),
                    StandardCharsets.UTF_8);
            this.sqlDetalle = new String(Files.readAllBytes(Paths.get("src/main/resources/detalleadm.sql")),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    @Transactional
    public ResumenAdm getResumenAdm(Integer rut, Integer ident) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", ident);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    params,
                    this::mapToResumenAdm);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }

    }

    private ResumenAdm mapToResumenAdm(ResultSet rs, int rowNum) throws SQLException {

        ResumenAdm resumenAdm = new ResumenAdm();

        resumenAdm.setAnio(rs.getInt("anio"));
        resumenAdm.setMaximo(rs.getDouble("cantidad_adm"));
        resumenAdm.setUsados(rs.getDouble("total_dias_ausencia"));
        resumenAdm.setSaldo(rs.getDouble("saldo"));

        return resumenAdm;

    }

    @Override
    public List<DetalleAdm> getDetalleAdmByRutAndIdent(Integer rut, Integer ident) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", ident);

        try {
            return namedParameterJdbcTemplate.query(
                    sqlDetalle,
                    params,
                    this::mapToDetalleAdm);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException(
                    "No se encontraron registros para el rut: " + rut + " y ident: " + ident);
        }
    }

    private DetalleAdm mapToDetalleAdm(ResultSet rs, int rowNum) throws SQLException {
        DetalleAdm detalleAdm = new DetalleAdm();
        detalleAdm.setNumero(rs.getInt("numero"));
        detalleAdm.setResolucion(rs.getString("resolucion"));
        detalleAdm.setFechaResolucion(rs.getDate("fechaResolucion").toLocalDate());
        detalleAdm.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
        detalleAdm.setFechaTermino(rs.getDate("fechaTermino").toLocalDate());
        detalleAdm.setPeriodo(rs.getDouble("periodo"));
        return detalleAdm;
    }

}
