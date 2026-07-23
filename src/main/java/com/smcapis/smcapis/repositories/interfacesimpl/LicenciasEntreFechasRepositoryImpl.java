package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.AusenciasLicenciasFuncioanarioDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.LicenciasEntreFechasRepository;

import org.springframework.core.io.Resource;

@Repository
public class LicenciasEntreFechasRepositoryImpl implements LicenciasEntreFechasRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String sql;

    public LicenciasEntreFechasRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {
            Resource resource = resourceLoader.getResource("classpath:sql/licenciasmedicasxmes.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<AusenciasLicenciasFuncioanarioDto> obtenerLicenciasMedicasByDepto(List<String> deptos, LocalDate fechaIni, LocalDate fechaFin) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("deptos", deptos);
        params.addValue("fechaIni", fechaIni);
        params.addValue("fechaFin", fechaFin);

        List<AusenciasLicenciasFuncioanarioDto> ausencias = namedParameterJdbcTemplate.query(sql,
                params,
                this::mapToFuncionarioDto);

        if (ausencias.isEmpty()) {
            return new ArrayList<>();
        }

        return ausencias;

    }

    private AusenciasLicenciasFuncioanarioDto mapToFuncionarioDto(ResultSet rs, int row) throws SQLException {

        return AusenciasLicenciasFuncioanarioDto.builder()
                .fechaInicio(rs.getDate("fechaini").toLocalDate())
                .fechaTermino(rs.getDate("termino").toLocalDate())
                .ident(rs.getInt("ident"))
                .rut(rs.getInt("rut"))
                .build();

    }

}
