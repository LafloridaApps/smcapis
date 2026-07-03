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

import com.smcapis.smcapis.dto.CargasFamiliaresDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.CargasFamiliaresRepository;
import org.springframework.core.io.Resource;

@Repository
public class CargasFamiliaresRepositoryImpl implements CargasFamiliaresRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sql;

    public CargasFamiliaresRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:sql/cargasfamiliares.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<CargasFamiliaresDto> getCargasFamiliaresByRut(Integer rut, Integer ident) {
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

    private CargasFamiliaresDto mapDto(ResultSet rs, int rowNum) throws SQLException {

        return new CargasFamiliaresDto.Builder()

                .apellidoPaterno(rs.getString("apellidopaterno"))
                .apellidoMaterno(rs.getString("apellidomaterno"))
                .nombres(rs.getString("nombres"))
                .fechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate())
                .fechaInicio(rs.getDate("fechainicio").toLocalDate())
                .fechaTermino(rs.getDate("fechatermino").toLocalDate())
                .fechaResol(rs.getDate("fecharesol").toLocalDate())
                .resol(rs.getString("resol"))
                .parentesco(rs.getString("descparentesco"))
                .build();

    }

}
