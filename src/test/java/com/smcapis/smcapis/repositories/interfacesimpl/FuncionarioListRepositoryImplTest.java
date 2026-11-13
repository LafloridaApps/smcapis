package com.smcapis.smcapis.repositories.interfacesimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.ResultSet;
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
        when(rs.getString("numresolcontr")).thenReturn(valor);
        when(rs.getString("obs_adicional_cont")).thenReturn(valor);
        when(jdbc.query(anyString(), any(SqlParameterSource.class),
                org.mockito.ArgumentMatchers.<RowMapper<FuncionarioContratoResponse>>any()))
                .thenAnswer(invocation -> {
                    assertTrue(invocation.<String>getArgument(0).contains("contratos.numresolcontr"));
                    assertTrue(invocation.<String>getArgument(0).contains("contratos.obs_adicional_cont"));
                    RowMapper<FuncionarioContratoResponse> mapper = invocation.getArgument(2);
                    return List.of(mapper.mapRow(rs, 0));
                });
        var repository = new FuncionarioListRepositoryImpl(jdbc, new DefaultResourceLoader());
        var contrato = repository.getFuncionario(123).getFirst();
        assertEquals(valor, contrato.numResolContr());
        assertEquals(valor, contrato.obsAdicionalCont());
    }
}
