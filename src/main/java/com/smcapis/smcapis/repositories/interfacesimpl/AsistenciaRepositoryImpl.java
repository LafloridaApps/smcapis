package com.smcapis.smcapis.repositories.interfacesimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smcapis.smcapis.dto.AsistenciaDto;
import com.smcapis.smcapis.dto.NewAsistenciaDto;
import com.smcapis.smcapis.expections.FileException;
import com.smcapis.smcapis.repositories.interfaces.AsistenciaRepository;
import org.springframework.core.io.Resource;

@Repository
public class AsistenciaRepositoryImpl implements AsistenciaRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sql;

    public AsistenciaRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

        try {

            Resource resourceRes = resourceLoader.getResource("classpath:sql/asistencia.sql");
            this.sql = new String(resourceRes.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new FileException("Error al leer el archivo SQL");
        }
    }

    @Override
    public List<AsistenciaDto> getAsistenciaByRutAndIdent(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", ident);
        params.addValue("fechaInicio", fechaInicio);
        params.addValue("fechaFin", fechaFin);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapDto);
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<>();
        }

    }

    private AsistenciaDto mapDto(ResultSet rs, int rowNum) throws SQLException {

        return new AsistenciaDto.Builder()
                .dia(rs.getString("dia"))
                .fechadia(rs.getString("fechadia"))
                .horaentman(rs.getString("horentman"))
                .horasalman(rs.getString("horsalman"))
                .horaenttar(rs.getString("horenttar"))
                .horasaltar(rs.getString("horsaltar"))
                .horentmantj(rs.getString("horentmantj"))
                .horsalmantj(rs.getString("horsalmantj"))
                .horenttartj(rs.getString("horenttartj"))
                .horsaltartj(rs.getString("horsaltartj"))
                .htrab(rs.getString("htrab"))
                .hext25(rs.getString("hext25"))
                .hext50(rs.getString("hext50"))
                .justinasautext(rs.getString("justinas_autext"))
                .hatr(rs.getString("hatrmm"))
                .build();

    }

    private NewAsistenciaDto mapDtoNew(ResultSet rs, int rowNum) throws SQLException {

        return NewAsistenciaDto.builder()
                .rut(rs.getString("rut"))
                .vrut(rs.getString("vrut"))
                .nombre(rs.getString("nombre"))
                .ficharj(rs.getString("ficharj"))
                .semana(rs.getString("semana"))
                .dia(rs.getString("dia"))
                .fechadia(rs.getString("fechadia"))
                .horentman(rs.getString("horentman"))
                .horsalman(rs.getString("horsalman"))
                .horenttar(rs.getString("horenttar"))
                .horsaltar(rs.getString("horsaltar"))
                .topeext25(rs.getString("topeext25"))
                .horentmantj(rs.getString("horentmantj"))
                .horsalmantj(rs.getString("horsalmantj"))
                .horenttartj(rs.getString("horenttartj"))
                .horsaltartj(rs.getString("horsaltartj"))
                .htrab(rs.getString("htrab"))
                .matrreal(rs.getString("matrreal"))
                .hatr(rs.getString("hatr"))
                .diasInas(rs.getString("DiasInas"))
                .hext25(rs.getString("hext25"))
                .hext50(rs.getString("hext50"))
                .justinasAutext(rs.getString("justinas_autext"))
                .tolerinfentman(rs.getString("tolerinfentman"))
                .tolersupentman(rs.getString("tolersupentman"))
                .tolerinfsalman(rs.getString("tolerinfsalman"))
                .tolersupsalman(rs.getString("tolersupsalman"))
                .tolerinfenttar(rs.getString("tolerinfenttar"))
                .tolersupenttar(rs.getString("tolersupenttar"))
                .tolerinfsaltar(rs.getString("tolerinfsaltar"))
                .tolersupsaltar(rs.getString("tolersupsaltar"))
                .feriado(rs.getString("feriado"))
                .sabado(rs.getString("sabado"))
                .domingo(rs.getString("domingo"))
                .inicio50mh(rs.getString("inicio50mh"))
                .fin50mh(rs.getString("fin50mh"))
                .inicio25m(rs.getString("inicio25m"))
                .fin25m(rs.getString("fin25m"))
                .inicio25t(rs.getString("inicio25t"))
                .fin25t(rs.getString("fin25t"))
                .inicio50m(rs.getString("inicio50m"))
                .fin50m(rs.getString("fin50m"))
                .inicio50t(rs.getString("inicio50t"))
                .fin50t(rs.getString("fin50t"))
                .hatrhh(rs.getString("hatrhh"))
                .hatrmm(rs.getString("hatrmm"))
                .hext25hh(rs.getString("hext25hh"))
                .hext25mm(rs.getString("hext25mm"))
                .hext50hh(rs.getString("hext50hh"))
                .hext50mm(rs.getString("hext50mm"))
                .depto(rs.getString("depto"))
                .nombreDepartamento(rs.getString("nombre_departamento"))
                .horasprogram25(rs.getString("horasprogram25"))
                .horasprogram50(rs.getString("horasprogram50"))
                .hturnohh(rs.getString("hturnohh"))
                .hturnomm(rs.getString("hturnomm"))
                .hExtDiurnos50(rs.getString("HExtDiurnos50"))
                .hExtDiurnos50hh(rs.getString("HExtDiurnos50hh"))
                .hExtDiurnos50mm(rs.getString("HExtDiurnos50mm"))
                .hExtNoc50(rs.getString("HExtNoc50"))
                .hExtNoc50hh(rs.getString("HExtNoc50hh"))
                .hExtNoc50mm(rs.getString("HExtNoc50mm"))
                .esTurno(rs.getString("EsTurno"))
                .htrabDiur(rs.getString("htrab_diur"))
                .htrabNoct(rs.getString("htrab_noct"))
                .horasTurnObligMes(rs.getString("horas_turn_oblig_mes"))
                .horasTurnNormalesMes(rs.getString("horas_turn_normales_mes"))
                .diasTurnoPermiso(rs.getString("dias_turno_permiso"))
                .horasTurnRecargo(rs.getString("horas_turn_recargo"))
                .horasTurnAl25(rs.getString("horas_turn_al_25"))
                .horasTurnAl50(rs.getString("horas_turn_al_50"))
                .horasTurnSum(rs.getString("horas_turn_sum"))
                .macRCEntMan(rs.getString("macRCEntMan"))
                .nombreRCEntMan(rs.getString("nombreRCEntMan"))
                .macRCSalMan(rs.getString("macRCSalMan"))
                .nombreRCSalMan(rs.getString("nombreRCSalMan"))
                .macRCEntTarde(rs.getString("macRCEntTarde"))
                .nombreRCEntTarde(rs.getString("nombreRCEntTarde"))
                .macRCSalTarde(rs.getString("macRCSalTarde"))
                .nombreRCSalTarde(rs.getString("nombreRCSalTarde"))
                .build();

    }

    @Override
    public List<NewAsistenciaDto> obtenerAsistenciaRut(Integer rut, Integer ident, LocalDate fechaInicio,
            LocalDate fechaFin) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rut", rut);
        params.addValue("ident", ident);
        params.addValue("fechaInicio", fechaInicio);
        params.addValue("fechaFin", fechaFin);

        try {
            return namedParameterJdbcTemplate.query(sql,
                    params,
                    this::mapDtoNew);
        } catch (EmptyResultDataAccessException e) {

            return new ArrayList<>();
        }
    }

}
