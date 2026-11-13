package com.smcapis.smcapis.mapper;

import com.smcapis.smcapis.dto.ItemLiquidacion;
import com.smcapis.smcapis.dto.RespuestaLiquidacionDetalle;
import com.smcapis.smcapis.utiles.NumeroUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LiquidacionMapper {

    public RespuestaLiquidacionDetalle toConsolidado(List<RespuestaLiquidacionDetalle> respuestas) {
        RespuestaLiquidacionDetalle primera = respuestas.getFirst();

        Set<String> codigosHaberes = respuestas.stream()
                .filter(res -> res.detalle() != null)
                .flatMap(res -> res.detalle().stream())
                .filter(i -> "HABER".equals(i.tipo()))
                .map(ItemLiquidacion::codigo)
                .collect(Collectors.toSet());

        Map<String, ItemLiquidacion> mapaItems = new LinkedHashMap<>();
        int totalHaberes = 0;
        int totalDescuentos = 0;
        int totalTributable = 0;
        int totalImpPrevisional = 0;
        int totalImpSalud = 0;
        int sueldoLiquido = 0;

        for (RespuestaLiquidacionDetalle res : respuestas) {
            totalHaberes += res.totalHaberes();
            totalDescuentos += res.totalDescuentos();
            totalTributable += res.totalTributable();
            totalImpPrevisional += res.totalImpPrevisional();
            totalImpSalud += res.totalImpSalud();
            sueldoLiquido += res.sueldoLiquido();

            if (res.detalle() != null) {
                for (ItemLiquidacion item : res.detalle()) {
                    // Se descartan los DESCUENTO cuyo codigo ya viene como HABER (ej: HRS.EXT.)
                    if ("DESCUENTO".equals(item.tipo()) && codigosHaberes.contains(item.codigo()) && item.nombre().contains("HRS.EXT") ) {
                        continue;
                    }
                    fusionarItem(mapaItems, item);
                }
            }
        }

        return primera.toBuilder()
                .detalle(new ArrayList<>(mapaItems.values()))
                .totalHaberes(totalHaberes)
                .totalDescuentos(totalDescuentos)
                .totalTributable(totalTributable)
                .totalImpPrevisional(totalImpPrevisional)
                .totalImpSalud(totalImpSalud)
                .sueldoLiquido(sueldoLiquido)
                .textoHorasExtras(null)
                .sueldoLiquidoPalabras(null)
                .build();
    }

    public RespuestaLiquidacionDetalle toEnriquecido(RespuestaLiquidacionDetalle original) {
        if (original == null)
            return null;
        String textoHE = generarTextoHorasExtras(original.detalle(), original.reparticion());
        String sueldoPalabras = NumeroUtils.numeroAPalabras(original.sueldoLiquido());
        return original.toBuilder()
                .textoHorasExtras(textoHE)
                .sueldoLiquidoPalabras(sueldoPalabras)
                .build();
    }

    private void fusionarItem(Map<String, ItemLiquidacion> mapaItems, ItemLiquidacion item) {
        String clave = item.tipo() + "|" + item.codigo();
        if (mapaItems.containsKey(clave)) {
            ItemLiquidacion existente = mapaItems.get(clave);
            mapaItems.put(clave, new ItemLiquidacion(
                    existente.liquidacionId(),
                    existente.tipo(),
                    existente.codigo(),
                    existente.nombre(),
                    existente.detalle(),
                    existente.valor() + item.valor()));
        } else {

            mapaItems.put(clave, new ItemLiquidacion(
                    item.liquidacionId(),
                    item.tipo(),
                    item.codigo(),
                    item.nombre(),
                    item.detalle(),
                    item.valor()));

        }
    }

    private String generarTextoHorasExtras(List<ItemLiquidacion> detalle, String reparticion) {
        if (detalle == null || detalle.isEmpty())
            return "";
        Map<String, String> etiquetas = Map.of("026", "HOR_EXTR(25%)", "029", "HOR_EXTR(50%)");
        String texto = detalle.stream()
                .filter(i -> "HABER".equals(i.tipo()) && ("026".equals(i.codigo()) || "029".equals(i.codigo())))
                .sorted((a, b) -> a.codigo().compareTo(b.codigo()))
                .map(i -> {
                    String cantidad = (i.detalle() != null && !i.detalle().isBlank()) ? i.detalle() : "";
                    return etiquetas.get(i.codigo()) + " " + cantidad;
                })
                .collect(Collectors.joining(", "));
        if (texto.isEmpty())
            return "";
        if (reparticion != null && !reparticion.isBlank())
            texto += " (" + reparticion + ")";
        return texto;
    }
}
