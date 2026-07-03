package com.smcapis.smcapis.services;

import com.smcapis.smcapis.config.ConfiguracionLiquidaciones;
import com.smcapis.smcapis.dto.ItemLiquidacion;
import com.smcapis.smcapis.dto.RespuestaLiquidacionDetalle;
import com.smcapis.smcapis.services.interfaces.LiquidacionesService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LiquidacionesServiceImpl implements LiquidacionesService {

    private final RestTemplate restTemplate;
    private final ConfiguracionLiquidaciones configuracion;

    public LiquidacionesServiceImpl(RestTemplate restTemplate, ConfiguracionLiquidaciones configuracion) {
        this.restTemplate = restTemplate;
        this.configuracion = configuracion;
    }

    @Override
    public RespuestaLiquidacionDetalle obtenerDetalle(Integer rut, Integer dominioId, Integer anio, Integer mes, Integer procesoId, String comuna) {
        return llamarApi(rut, dominioId, anio, mes, procesoId, comuna);
    }

    @Override
    public RespuestaLiquidacionDetalle obtenerDetalleConsolidado(Integer rut, Integer dominioId, Integer anio, Integer mes, List<Integer> procesoIds, String comuna) {
        List<RespuestaLiquidacionDetalle> respuestas = new ArrayList<>();

        for (Integer procesoId : procesoIds) {
            RespuestaLiquidacionDetalle respuesta = llamarApi(rut, dominioId, anio, mes, procesoId, comuna);
            if (respuesta != null) {
                respuestas.add(respuesta);
            }
        }

        if (respuestas.isEmpty()) {
            return null;
        }

        return consolidar(respuestas);
    }

    private RespuestaLiquidacionDetalle llamarApi(Integer rut, Integer dominioId, Integer anio, Integer mes, Integer procesoId, String comuna) {
        String url = UriComponentsBuilder.fromUriString(configuracion.getBaseUrl() + "/detalle.ashx")
                .queryParam("rut", rut)
                .queryParam("dominio_id", dominioId)
                .queryParam("anio", anio)
                .queryParam("mes", mes)
                .queryParam("proceso_id", procesoId)
                .queryParam("COMUNA", comuna)
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                RespuestaLiquidacionDetalle.class
        ).getBody();
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
                primera.retencion3PorcientoPrestamoSolidario()
        );
    }

}
