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

import com.smcapis.smcapis.dto.LicenciasMedicasFuncionarioDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.LicenciasMedicasRepository;
import org.springframework.core.io.Resource;

@Repository
public class LicenciasMedicasRepositoryImpl implements LicenciasMedicasRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sql;

    public LicenciasMedicasRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:sql/licenciasmedicas.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<LicenciasMedicasFuncionarioDto> getLicenciasMedicasByRut(Integer rut, Integer ident) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", ident);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapDto);
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<>();
        }
    }

    private LicenciasMedicasFuncionarioDto mapDto(ResultSet rs, int rowNum) throws SQLException {

        return new LicenciasMedicasFuncionarioDto.Builder()
                .fechaInicio(rs.getDate("fechaini").toLocalDate())
                .fechaRecepcion(rs.getDate("fecharecepcion").toLocalDate())
                .numlic(rs.getLong("numlic"))
                .diaslic(rs.getInt("diaslic"))
                .tipoLicencia(rs.getString("desctipolic"))
                .build();

    }

}
