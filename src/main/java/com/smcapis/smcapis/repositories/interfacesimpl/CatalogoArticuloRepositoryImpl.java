package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.smcapis.smcapis.dto.CatalogoArticuloResponse;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.mapper.CatalogoArticuloMapper;
import com.smcapis.smcapis.repositories.interfaces.CatalogoArticuloRepository;

@Repository
public class CatalogoArticuloRepositoryImpl implements CatalogoArticuloRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CatalogoArticuloMapper mapper;
    private final String sql;

    public CatalogoArticuloRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate,
            ResourceLoader resourceLoader, CatalogoArticuloMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        try (var input = resourceLoader.getResource("classpath:sql/catalogoarticulos.sql").getInputStream()) {
            this.sql = new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer catalogoarticulos.sql");
        }
    }

    @Override
    public List<CatalogoArticuloResponse> obtenerArticulos() {
        return jdbcTemplate.query(sql, Map.of(), mapper::mapToCatalogoArticuloResponse);
    }
}
