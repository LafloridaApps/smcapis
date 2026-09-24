package com.smcapis.smcapis.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;
import com.smcapis.smcapis.dto.CatalogoArticuloResponse;

@Component
public class CatalogoArticuloMapper {

    public CatalogoArticuloResponse mapToCatalogoArticuloResponse(ResultSet rs, int rowNum)
            throws SQLException {
        return new CatalogoArticuloResponse(
                rs.getString("codgrupoart"),
                rs.getString("codsubgrupoart"),
                rs.getString("codrubroart"),
                rs.getString("codart"),
                rs.getString("nombreart"));
    }
}
