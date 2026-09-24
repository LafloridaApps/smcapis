package com.smcapis.smcapis.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;
import com.smcapis.smcapis.dto.ArticuloResponse;
import com.smcapis.smcapis.utiles.UtilsHelper;
import com.smcapis.smcapis.dto.ArticuloDatosAdicionales;
import com.smcapis.smcapis.repositories.interfaces.DatosAdicionalesRepository;

@Component
public class ArticuloMapper {

    private final DatosAdicionalesRepository datosAdicionales;

    public ArticuloMapper(DatosAdicionalesRepository datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    public ArticuloResponse mapToArticuloResponse(ResultSet rs, int rowNum)
            throws SQLException {

        String codInv = rs.getString("codinv");

        List<ArticuloDatosAdicionales> adicionales = datosAdicionales.getAdicionalesByCodInv(Integer.parseInt(codInv));

        return new ArticuloResponse(
                rowNum,
                rs.getInt("coddocaf"),
                rs.getInt("numerodocaf"),
                rs.getInt("anodocaf"),
                rs.getInt("rut"),
                rs.getInt("nroorden"),
                UtilsHelper.formatFecha(rs.getString("fechaorden")),
                rs.getInt("nrofactura"),
                UtilsHelper.formatFecha(rs.getString("fechafactura")),
                rs.getString("codgrupoart"),
                rs.getString("codsubgrupoart"),
                rs.getString("codrubroart"),
                rs.getString("codart"),
                rs.getString("codinv"),
                rs.getInt("vutilinv"),
                rs.getInt("valorinv"),
                UtilsHelper.formatFecha(rs.getString("fechadocaf")),
                rs.getString("depto"),
                rs.getString("nombre_departamento"),
                rs.getString("jefe_departamento"),
                rs.getString("nombreoficina"),
                rs.getString("responsableoficina"),
                rs.getString("cargooficina"),
                rs.getString("nombreart"),
                adicionales,
                rs.getString("DESCMAEINV")

        );

    }

}
