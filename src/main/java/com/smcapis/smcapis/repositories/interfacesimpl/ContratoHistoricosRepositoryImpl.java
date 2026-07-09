package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.ContratoDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.ContratosHistoricosRepository;

import org.springframework.core.io.Resource;

@Repository
public class ContratoHistoricosRepositoryImpl implements ContratosHistoricosRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sql;

    public ContratoHistoricosRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:sql/contratos-historicos.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<ContratoDto> getContratosByRut(Integer rut) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);

        List<ContratoDto> funcionarios = namedParameterJdbcTemplate.query(sql, params,

                this::mapToFuncionarioDto);

        if (funcionarios.isEmpty()) {
            return new ArrayList<>();
        }

        return funcionarios;
    }

    private ContratoDto mapToFuncionarioDto(ResultSet rs, int row) throws SQLException {

        java.sql.Date fechaIni = rs.getDate("fechaini");
        java.sql.Date fechaFi = rs.getDate("fechafin");
        java.sql.Date fechaResol = rs.getDate("fecharesolcontr");

        return ContratoDto.builder()
                .departamento(rs.getString("departamento"))
                .ident(rs.getInt("ident"))
                .tipoContrato(rs.getString("tipocontrato"))
                .grado(rs.getInt("grado"))
                .nombreescalafon(rs.getString("nombreescalafon"))
                .fechaInicio(fechaIni != null ? fechaIni.toLocalDate() : null)
                .fechaFin(fechaFi != null ? fechaFi.toLocalDate() : null)
                .fechaResolContr(fechaResol != null ? fechaResol.toLocalDate() : null)
                .numeroSolContr(rs.getString("numresolcontr"))
                .obscontre(rs.getString("obscontre"))

                .build();

    }

}
