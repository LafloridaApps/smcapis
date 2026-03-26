package com.smcapis.smcapis.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;
import com.smcapis.smcapis.dto.ArticuloResponse;
import com.smcapis.smcapis.utiles.UtilsHelper;
import com.smcapis.smcapis.dto.ArticuloDatosAdicionales;
import com.smcapis.smcapis.repositories.interfaces.DatosAdicionalesService;

@Component
public class ArticuloMapper {

    private final DatosAdicionalesService datosAdicionales;

    public ArticuloMapper(DatosAdicionalesService datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    public ArticuloResponse mapToArticuloResponse(ResultSet rs, int rowNum)
            throws SQLException {

        String codigoGrupo = rs.getString("codgrupoart");
        String codSubGrupoArt = rs.getString("codsubgrupoart");
        String codRubroArt = rs.getString("codrubroart");
        String codArt = rs.getString("codart");
        String codInv = rs.getString("codinv");

        String cadena = codigoGrupo.concat(codSubGrupoArt).concat(codRubroArt).concat(codArt);

        List<ArticuloDatosAdicionales> adicionales = datosAdicionales.getAdicionalesByCodigo(cadena,
                Integer.parseInt(codInv));

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
                adicionales

        );

    }

   

}
