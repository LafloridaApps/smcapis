package com.smcapis.smcapis.services;

import com.smcapis.smcapis.config.ConfiguracionLiquidaciones;
import com.smcapis.smcapis.dto.ItemLiquidacion;
import com.smcapis.smcapis.dto.RespuestaLiquidacionDetalle;
import com.smcapis.smcapis.expections.RecursoNoEncontradoException;
import com.smcapis.smcapis.services.interfaces.LiquidacionesService;
import com.smcapis.smcapis.utiles.NumeroUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LiquidacionesServiceImpl implements LiquidacionesService {

    private final RestTemplate restTemplate;
    private final ConfiguracionLiquidaciones configuracion;
    private final ObjectMapper objectMapper;

    public LiquidacionesServiceImpl(RestTemplate restTemplate, ConfiguracionLiquidaciones configuracion, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.configuracion = configuracion;
        this.objectMapper = objectMapper;
    }

    @Override
    public RespuestaLiquidacionDetalle obtenerDetalle(Integer rut, Integer dominioId, Integer anio, Integer mes, Integer procesoId) {
        RespuestaLiquidacionDetalle respuesta = llamarApi(rut, dominioId, anio, mes, procesoId);
        if (!esRespuestaValida(respuesta)) {
            throw new RecursoNoEncontradoException("La busqueda no arrojo resultados.");
        }
        return enriquecer(respuesta);
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

        return enriquecer(consolidar(respuestas));
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

    private RespuestaLiquidacionDetalle consolidar(List<RespuestaLiquidacionDetalle> respuestas) {
        RespuestaLiquidacionDetalle primera = respuestas.getFirst();

        Map<String, ItemLiquidacion> mapaItems = new LinkedHashMap<>();
        int totalHaberes = 0;
        int totalDescuentos = 0;
        int totalTributable = 0;
        int totalImpPrevisional = 0;
        int totalImpSalud = 0;
        int sueldoLiquido = 0;
        int diasTrabajados = 0;

        for (RespuestaLiquidacionDetalle res : respuestas) {
            totalHaberes += res.totalHaberes();
            totalDescuentos += res.totalDescuentos();
            totalTributable += res.totalTributable();
            totalImpPrevisional += res.totalImpPrevisional();
            totalImpSalud += res.totalImpSalud();
            sueldoLiquido += res.sueldoLiquido();
            diasTrabajados += res.diasTrabajados();

            if (res.detalle() != null) {
                for (ItemLiquidacion item : res.detalle()) {
                    String clave = item.tipo() + "|" + item.codigo();
                    if (mapaItems.containsKey(clave)) {
                        ItemLiquidacion existente = mapaItems.get(clave);
                        ItemLiquidacion fusionado = new ItemLiquidacion(
                                existente.liquidacionId(),
                                existente.tipo(),
                                existente.codigo(),
                                existente.nombre(),
                                existente.detalle(),
                                existente.valor() + item.valor()
                        );
                        mapaItems.put(clave, fusionado);
                    } else {
                        mapaItems.put(clave, item);
                    }
                }
            }
        }

        return new RespuestaLiquidacionDetalle(
                primera.liquidacionId(),
                primera.anio(),
                primera.mes(),
                primera.nombreCompleto(),
                primera.rut(),
                primera.codigo(),
                primera.grado(),
                primera.nivel(),
                primera.titulo(),
                primera.jornada(),
                primera.proceso(),
                primera.reparticion(),
                primera.cheque(),
                primera.cajaPrevisional(),
                primera.institucionSalud(),
                primera.bienios(),
                primera.cargaFamiliar(),
                new ArrayList<>(mapaItems.values()),
                totalHaberes,
                totalDescuentos,
                totalTributable,
                totalImpPrevisional,
                totalImpSalud,
                sueldoLiquido,
                diasTrabajados,
                primera.cotizPactadaIsapre(),
                primera.porcentajeImp(),
                primera.fechaAntiguedad(),
                primera.horasExtras(),
                primera.incDL3551(),
                primera.asigResponsab(),
                primera.asigFamiliar(),
                primera.planSuplem(),
                primera.aporteSegInvSob(),
                primera.segCesantiaEmp(),
                primera.asigCritica(),
                primera.asigLey18717(),
                primera.asigDireccionSuper(),
                primera.horasExtras50porc(),
                primera.gastosRepresenta(),
                primera.mutual(),
                primera.fondoBonoLaboral(),
                primera.difHabRetroactivos(),
                primera.montoImponible(),
                primera.montoImpDeshaucio(),
                primera.ahVoluntario(),
                primera.retJudicial(),
                primera.ahorPrevVol(),
                primera.segCesantia(),
                primera.hdiFidelidad(),
                primera.hdiConductores(),
                primera.anticipoArriendoCasaFiscal(),
                primera.casasCorfo(),
                primera.bienestarMOP(),
                primera.mutualSeguros(),
                primera.aporteBienestarMunicipal(),
                primera.prestamoBienestarMunicipal(),
                primera.coopeuch(),
                primera.prestamosCoopeuch(),
                primera.asocProfesionalesGobiernoRegional(),
                primera.prestamoAraucana(),
                primera.hogarDeCristo(),
                primera.seguroInvalidezSobrevivenciaEmpresa(),
                primera.ciaSeguroConfuturo(),
                primera.flgSportlife(),
                primera.seguroHogarAraucana(),
                primera.seguroVidaAraucana(),
                primera.apvAFPCapital(),
                primera.achs(),
                primera.atrasos(),
                primera.retencion3PorcientoPrestamoSolidario(),
                null,
                null
        );
    }

    private RespuestaLiquidacionDetalle enriquecer(RespuestaLiquidacionDetalle original) {
        if (original == null) return null;
        String textoHE = generarTextoHorasExtras(original.detalle(), original.reparticion());
        String sueldoPalabras = NumeroUtils.numeroAPalabras(original.sueldoLiquido());
        return new RespuestaLiquidacionDetalle(
                original.liquidacionId(),
                original.anio(),
                original.mes(),
                original.nombreCompleto(),
                original.rut(),
                original.codigo(),
                original.grado(),
                original.nivel(),
                original.titulo(),
                original.jornada(),
                original.proceso(),
                original.reparticion(),
                original.cheque(),
                original.cajaPrevisional(),
                original.institucionSalud(),
                original.bienios(),
                original.cargaFamiliar(),
                original.detalle(),
                original.totalHaberes(),
                original.totalDescuentos(),
                original.totalTributable(),
                original.totalImpPrevisional(),
                original.totalImpSalud(),
                original.sueldoLiquido(),
                original.diasTrabajados(),
                original.cotizPactadaIsapre(),
                original.porcentajeImp(),
                original.fechaAntiguedad(),
                original.horasExtras(),
                original.incDL3551(),
                original.asigResponsab(),
                original.asigFamiliar(),
                original.planSuplem(),
                original.aporteSegInvSob(),
                original.segCesantiaEmp(),
                original.asigCritica(),
                original.asigLey18717(),
                original.asigDireccionSuper(),
                original.horasExtras50porc(),
                original.gastosRepresenta(),
                original.mutual(),
                original.fondoBonoLaboral(),
                original.difHabRetroactivos(),
                original.montoImponible(),
                original.montoImpDeshaucio(),
                original.ahVoluntario(),
                original.retJudicial(),
                original.ahorPrevVol(),
                original.segCesantia(),
                original.hdiFidelidad(),
                original.hdiConductores(),
                original.anticipoArriendoCasaFiscal(),
                original.casasCorfo(),
                original.bienestarMOP(),
                original.mutualSeguros(),
                original.aporteBienestarMunicipal(),
                original.prestamoBienestarMunicipal(),
                original.coopeuch(),
                original.prestamosCoopeuch(),
                original.asocProfesionalesGobiernoRegional(),
                original.prestamoAraucana(),
                original.hogarDeCristo(),
                original.seguroInvalidezSobrevivenciaEmpresa(),
                original.ciaSeguroConfuturo(),
                original.flgSportlife(),
                original.seguroHogarAraucana(),
                original.seguroVidaAraucana(),
                original.apvAFPCapital(),
                original.achs(),
                original.atrasos(),
                original.retencion3PorcientoPrestamoSolidario(),
                textoHE,
                sueldoPalabras
        );
    }

    private String generarTextoHorasExtras(List<ItemLiquidacion> detalle, String reparticion) {
        if (detalle == null || detalle.isEmpty()) return "";
        Map<String, String> etiquetas = Map.of("026", "HOR_EXTR(25%)", "029", "HOR_EXTR(50%)");
        String texto = detalle.stream()
                .filter(i -> "026".equals(i.codigo()) || "029".equals(i.codigo()))
                .sorted((a, b) -> a.codigo().compareTo(b.codigo()))
                .map(i -> etiquetas.get(i.codigo()) + " $" + i.valor())
                .collect(Collectors.joining(", "));
        if (texto.isEmpty()) return "";
        if (reparticion != null && !reparticion.isBlank()) texto += " (" + reparticion + ")";
        return texto;
    }

}
