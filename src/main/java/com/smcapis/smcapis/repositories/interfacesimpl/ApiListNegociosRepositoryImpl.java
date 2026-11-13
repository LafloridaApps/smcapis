package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.DetalleNegocio;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.ApiListNegociosRepository;
import org.springframework.core.io.Resource;

@Repository
public class ApiListNegociosRepositoryImpl implements ApiListNegociosRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String sql;

    public ApiListNegociosRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {
            Resource resource = resourceLoader.getResource("classpath:sql/negocios.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<DetalleNegocio> obtenerListaNegociosRut(Integer rut) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapToAusencia);
        } catch (EmptyResultDataAccessException e) {

            return Collections.emptyList();
        }
    }

    private DetalleNegocio mapToAusencia(ResultSet rs, int rowNum) throws SQLException {

        return new DetalleNegocio(
                rs.getInt("clave"),
                rs.getInt("rol"),
                rs.getString("descripcalle"),
                rs.getInt("dirnumero"),
                rs.getString("diraclara"),
                rs.getString("rol_sii"),
                rs.getString("vrol_sii"),
                rs.getString("fechainicioneg"),
                rs.getString("nombre_fantasia"),
                rs.getString("rut_rep_legal"),
                rs.getString("nombre_rep_legal"),
                rs.getString("direc_rep_legal"),
                rs.getInt("rut_arriendo"),
                rs.getString("nombre_arrendatario"),
                rs.getString("clase_alcohol")
            
            );

    }

}
