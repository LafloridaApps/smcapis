package com.smcapis.smcapis.utiles;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UtilsHelper {

    private UtilsHelper() {

    }

    public static Map<String, String> obtieneCodigoInv(String codigo) {

        Map<String, String> map = new HashMap<>();

        String codgrupoart = codigo.substring(0, 1);
        String codsubgrupoart = codigo.substring(1, 3);
        String codrubroart = codigo.substring(3, 5);
        String codart = codigo.substring(5, codigo.length());

        map.put("codgrupo", codgrupoart);
        map.put("codsubgrupo", codsubgrupoart);
        map.put("codrubro", codrubroart);
        map.put("codigo", codart);

        return map;

    }

    public static LocalDate formatFecha(String fecha) {

        if(fecha == null){
            return null;
        }

        String formatFecha = fecha.substring(0, 10);

        return LocalDate.parse(formatFecha);

    }

}
