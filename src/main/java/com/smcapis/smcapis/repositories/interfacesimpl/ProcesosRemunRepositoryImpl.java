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

import com.smcapis.smcapis.dto.ProcesosRemunDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.ProcesosRemunRepository;

import org.springframework.core.io.Resource;

@Repository
public class ProcesosRemunRepositoryImpl implements ProcesosRemunRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String sql;

    public ProcesosRemunRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {
            Resource resource = resourceLoader.getResource("classpath:sql/reprocesos.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<ProcesosRemunDto> obtenerProcesos(Integer rut, Integer dominioId, Integer anio) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", dominioId);
        params.addValue("anio", anio);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapToDto);
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<>();
        }
    }

    private ProcesosRemunDto mapToDto(ResultSet rs, int rowNum) throws SQLException {

        return new ProcesosRemunDto(
                rs.getInt("ident"),
                rs.getInt("anoremun"),
                rs.getInt("mesremun"),
                rs.getString("descripcionproceso"),
                rs.getInt("nroliq"),
                rs.getBoolean("LIQVISIBLEWEB")

        );

    }

}
