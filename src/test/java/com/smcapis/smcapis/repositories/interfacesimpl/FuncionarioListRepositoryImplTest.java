package com.smcapis.smcapis.repositories.interfacesimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Locale;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import com.smcapis.smcapis.dto.FuncionarioContratoResponse;

class FuncionarioListRepositoryImplTest {
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"Resolución 001/2026", ""})
    void mapeaCamposAdicionalesSinPerderNulosNiTexto(String valor) throws Exception {
        var jdbc = mock(NamedParameterJdbcTemplate.class);
        var rs = mock(ResultSet.class);
        when(rs.getString("denominacion_dominio")).thenReturn(valor);
        when(rs.getString("numresolcontr")).thenReturn(valor);
        when(rs.getString("obs_adicional_cont")).thenReturn(valor);
        Integer linea = valor == null ? null : 7;
        LocalDate fecha = valor == null ? null : LocalDate.of(2026, 2, 12);
        when(rs.getObject("lincontrato", Integer.class)).thenReturn(linea);
        when(rs.getDate("fecharesolcontr")).thenReturn(fecha == null ? null : Date.valueOf(fecha));
        when(jdbc.query(anyString(), any(SqlParameterSource.class),
                org.mockito.ArgumentMatchers.<RowMapper<FuncionarioContratoResponse>>any()))
                .thenAnswer(invocation -> {
                    assertTrue(invocation.<String>getArgument(0).contains("contratos.numresolcontr"));
                    assertTrue(invocation.<String>getArgument(0).contains("contratos.obs_adicional_cont"));
                    String sql = invocation.<String>getArgument(0).toLowerCase(Locale.ROOT);
                    assertTrue(sql.contains("contratos.lincontrato as lincontrato"));
                    assertTrue(sql.contains("contratos.fecharesolcontr as fecharesolcontr"));
                    RowMapper<FuncionarioContratoResponse> mapper = invocation.getArgument(2);
                    return List.of(mapper.mapRow(rs, 0));
                });
        var repository = new FuncionarioListRepositoryImpl(jdbc, new DefaultResourceLoader());
        var contrato = repository.getFuncionario(123).getFirst();
        assertEquals(valor, contrato.denominacionDominio());
        assertEquals(valor, contrato.numResolContr());
        assertEquals(valor, contrato.obsAdicionalCont());
        assertEquals(linea, contrato.linContrato());
        assertEquals(fecha, contrato.fechaResolContr());
    }
}
