package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.core.io.Resource;

import com.smcapis.smcapis.dto.DepartamentoDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.DepartamentosRepository;

@Repository
public class DepartamentoRepositoryImpl  implements DepartamentosRepository {


    private final JdbcTemplate jdbcTemplate;

    private final String sql;

      public DepartamentoRepositoryImpl(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;

        try {
            Resource resource = resourceLoader.getResource("classpath:departamentos.sql");
            this.sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<DepartamentoDto> getDepartamentos() {

        try {
            return jdbcTemplate.query(sql,
                    this::mapToDepartamentoDto);
        } catch (EmptyResultDataAccessException e) {

            throw new EmptyResultDataAccessException("No se encontraron departamentos", 1);
        }
    }



     private DepartamentoDto mapToDepartamentoDto(ResultSet rs, int row) throws SQLException {
       return new DepartamentoDto(rs.getString("depto"), rs.getString("nombre_departamento"));
      
    }

}
