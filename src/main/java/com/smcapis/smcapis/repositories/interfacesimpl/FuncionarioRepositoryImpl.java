package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.FuncionarioDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.FuncionarioRespository;
import com.smcapis.smcapis.utiles.FotoUtils;

@Repository
public class FuncionarioRepositoryImpl implements FuncionarioRespository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String sql;

    public FuncionarioRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {
            this.sql = new String(Files.readAllBytes(Paths.get("src/main/resources/funcionario.sql")),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public FuncionarioDto getFuncionario(Integer rut) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    params,
                    this::mapToFuncionarioDto);
        } catch (EmptyResultDataAccessException e) {

            throw new EmptyResultDataAccessException("No se encontró funcionario con rut: " + rut, 1);
        }

    }

    private FuncionarioDto mapToFuncionarioDto(ResultSet rs, int row) throws SQLException {
        FuncionarioDto funcionarioDto = new FuncionarioDto();
        funcionarioDto.setRut(rs.getInt("rut"));
        funcionarioDto.setVrut(rs.getString("vrut"));
        funcionarioDto.setEmail(rs.getString("email"));
        funcionarioDto.setPaterno(rs.getString("apellidopaterno"));
        funcionarioDto.setMaterno(rs.getString("apellidomaterno"));
        funcionarioDto.setNombres(rs.getString("nombres"));

        funcionarioDto.setCodDeptoExt(rs.getString("coddepto"));
        funcionarioDto.setIdent(rs.getInt("ident"));

        byte[] imageBytes = rs.getBytes("foto");
        funcionarioDto.setFoto(imageBytes != null ? FotoUtils.fotoConverter(imageBytes) : " ");
        funcionarioDto.setDepartamento(rs.getString("departamento"));
        return funcionarioDto;
    }

}
