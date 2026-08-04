package com.smcapis.smcapis.services;

import com.smcapis.smcapis.config.ConfiguracionLiquidaciones;
import com.smcapis.smcapis.dto.RespuestaLiquidacionDetalle;
import com.smcapis.smcapis.expections.RecursoNoEncontradoException;
import com.smcapis.smcapis.mapper.LiquidacionMapper;
import com.smcapis.smcapis.services.interfaces.LiquidacionesService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class LiquidacionesServiceImpl implements LiquidacionesService {

    private final RestTemplate restTemplate;
    private final ConfiguracionLiquidaciones configuracion;
    private final ObjectMapper objectMapper;
    private final LiquidacionMapper liquidacionMapper;

    public LiquidacionesServiceImpl(RestTemplate restTemplate, ConfiguracionLiquidaciones configuracion, ObjectMapper objectMapper, LiquidacionMapper liquidacionMapper) {
        this.restTemplate = restTemplate;
        this.configuracion = configuracion;
        this.objectMapper = objectMapper;
        this.liquidacionMapper = liquidacionMapper;
    }

    @Override
    public RespuestaLiquidacionDetalle obtenerDetalle(Integer rut, Integer dominioId, Integer anio, Integer mes, Integer procesoId) {
        RespuestaLiquidacionDetalle respuesta = llamarApi(rut, dominioId, anio, mes, procesoId);
        if (!esRespuestaValida(respuesta)) {
            throw new RecursoNoEncontradoException("La busqueda no arrojo resultados.");
        }
        return liquidacionMapper.toEnriquecido(respuesta);
    }

    @Override
    public RespuestaLiquidacionDetalle obtenerDetalleConsolidado(Integer rut, Integer dominioId, Integer anio, Integer mes, List<Integer> procesoIds) {
        List<RespuestaLiquidacionDetalle> respuestas = new ArrayList<>();

        for (Integer procesoId : procesoIds) {
            RespuestaLiquidacionDetalle respuesta = llamarApi(rut, dominioId, anio, mes, procesoId);
            if (esRespuestaValida(respuesta)) {
                respuestas.add(respuesta);
            }
        }

        if (respuestas.isEmpty()) {
            throw new RecursoNoEncontradoException("La busqueda no arrojo resultados.");
        }

        return liquidacionMapper.toEnriquecido(liquidacionMapper.toConsolidado(respuestas));
    }

    private boolean esRespuestaValida(RespuestaLiquidacionDetalle respuesta) {
        return respuesta != null;
    }

    private RespuestaLiquidacionDetalle llamarApi(Integer rut, Integer dominioId, Integer anio, Integer mes, Integer procesoId) {
        String url = UriComponentsBuilder.fromUriString(configuracion.getBaseUrl() + "/detalle.ashx")
                .queryParam("rut", rut)
                .queryParam("dominio_id", dominioId)
                .queryParam("anio", anio)
                .queryParam("mes", mes)
                .queryParam("proceso_id", procesoId)
                .queryParam("COMUNA", "LA_FLORIDA")
                .toUriString();

        try {
            String json = restTemplate.getForObject(url, String.class);
            if (json == null || json.contains("\"error\"")) {
                return null;
            }
            return objectMapper.readValue(json, RespuestaLiquidacionDetalle.class);
        } catch (Exception e) {
            return null;
        }
    }

}
