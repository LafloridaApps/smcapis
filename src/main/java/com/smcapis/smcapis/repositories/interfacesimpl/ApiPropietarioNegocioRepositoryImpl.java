package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.DetalleNegocio;
import com.smcapis.smcapis.dto.PropietarioNegocio;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.ApiListNegociosRepository;
import com.smcapis.smcapis.repositories.interfaces.ApiPropietarioNegocioRepository;

import org.springframework.core.io.Resource;

@Repository 
public class ApiPropietarioNegocioRepositoryImpl implements ApiPropietarioNegocioRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ApiListNegociosRepository apiListNegociosRepository;

    private String sql;
    private static final Logger logger = LoggerFactory.getLogger(ApiPropietarioNegocioRepositoryImpl.class);

    public ApiPropietarioNegocioRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader, ApiListNegociosRepository apiListNegociosRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.apiListNegociosRepository = apiListNegociosRepository;

        try {
            Resource resource = resourceLoader.getResource("classpath:sql/propietarionegocio.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public PropietarioNegocio obtenerPropietarioNegocio(Integer rut) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    params,
                    this::mapToDto);

        } catch (EmptyResultDataAccessException e) {
            return null; // Es el comportamiento esperado si no se encuentran registros
        } catch (Exception e) {
            logger.error("Error al buscar rut {}", rut, e);
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_GATEWAY, "No se pudo consultar el código", e);
        }
    }

    private PropietarioNegocio mapToDto(ResultSet rs, int rowNum) throws SQLException {

        Integer rut = rs.getInt("rut");

        List<DetalleNegocio> negocios = apiListNegociosRepository.obtenerListaNegociosRut(rut);

        return PropietarioNegocio
                .builder()
                .rut(rut)
                .vrut(rs.getString("vrut"))
                .nombre(rs.getString("nombre"))
                .direccion(rs.getString("direccion"))
                .fono(rs.getString("fono"))
                .email(rs.getString("email"))
                .negocios(negocios)
                .build();

    }

}
