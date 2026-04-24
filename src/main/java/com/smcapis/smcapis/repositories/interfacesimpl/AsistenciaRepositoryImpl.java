package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.AsistenciaDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.AsistenciaRepository;
import org.springframework.core.io.Resource;

@Repository
public class AsistenciaRepositoryImpl implements AsistenciaRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sql;

    public AsistenciaRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:asistencia.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<AsistenciaDto> getAsistenciaByRutAndIdent(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", ident);
        params.addValue("fechaInicio", fechaInicio);
        params.addValue("fechaFin", fechaFin);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapDto);
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<>();
        }

    }

    private AsistenciaDto mapDto(ResultSet rs, int rowNum) throws SQLException {

        return new AsistenciaDto.Builder()
                .dia(rs.getString("dia"))
                .fechadia(rs.getString("fechadia"))
                .horaentman(rs.getString("horentman"))
                .horasalman(rs.getString("horsalman"))
                .horaenttar(rs.getString("horenttar"))
                .horasaltar(rs.getString("horsaltar"))
                .horentmantj(rs.getString("horentmantj"))
                .horsalmantj(rs.getString("horsalmantj"))
                .horenttartj(rs.getString("horenttartj"))
                .horsaltartj(rs.getString("horsaltartj"))
                .htrab(rs.getString("htrab"))
                .hext25(rs.getString("hext25"))
                .hext50(rs.getString("hext50"))
                .justinasautext(rs.getString("justinas_autext"))
                .hatr(rs.getString("hatr"))
                .build();

    }

}
