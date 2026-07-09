package com.smcapis.smcapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smcapis.smcapis.dto.AnotacionesFuncionarioDto;
import com.smcapis.smcapis.dto.CargasFamiliaresDto;
import com.smcapis.smcapis.dto.ContratoDto;
import com.smcapis.smcapis.dto.CursoFuncionarioDto;
import com.smcapis.smcapis.dto.FichaFuncionarioDto;
import com.smcapis.smcapis.dto.LicenciasMedicasFuncionarioDto;
import com.smcapis.smcapis.dto.ProfesionDto;
import com.smcapis.smcapis.services.interfaces.AnotacionesService;
import com.smcapis.smcapis.services.interfaces.CargasFamilaresService;
import com.smcapis.smcapis.services.interfaces.ContratosHistoricosService;
import com.smcapis.smcapis.services.interfaces.CursosService;
import com.smcapis.smcapis.services.interfaces.FichaFuncionarioService;
import com.smcapis.smcapis.services.interfaces.LicenciasMedicasService;
import com.smcapis.smcapis.services.interfaces.ProfesionesService;

@Service
public class FichaFuncionarioServiceImpl implements FichaFuncionarioService {

    private final AnotacionesService anotacionesService;
    private final LicenciasMedicasService licenciasMedicasService;
    private final CargasFamilaresService cargasFamilaresService;
    private final ProfesionesService profesionService;
    private final CursosService cursoService;
    private final ContratosHistoricosService contratosHistoricosService;

    public FichaFuncionarioServiceImpl(
            AnotacionesService anotacionesService,
            LicenciasMedicasService licenciasMedicasService,
            CargasFamilaresService cargasFamilaresService,
            ProfesionesService profesionService,
            CursosService cursoService,
            ContratosHistoricosService contratosHistoricosService) {
        this.anotacionesService = anotacionesService;
        this.licenciasMedicasService = licenciasMedicasService;
        this.cargasFamilaresService = cargasFamilaresService;
        this.profesionService = profesionService;
        this.cursoService = cursoService;
        this.contratosHistoricosService = contratosHistoricosService;
    }

    @Override
    public FichaFuncionarioDto getFichaByRut(Integer rut, Integer ident) {

        return new FichaFuncionarioDto.Builder()
                .rut(rut)
                .ident(ident)
                .anotaciones(anotacionesFuncionario(rut, ident))
                .licenciasMedicas(licenciasMedicasFuncionario(rut, ident))
                .cargasFamiliares(cargasFamiliaresFuncionario(rut, ident))
                .profesiones(profesionesFuncionario(rut))
                .cursos(cursosFuncionario(rut, ident))
                .contratos(contratosHistoricosFuncionario(rut))
                .build();

    }

    private List<AnotacionesFuncionarioDto> anotacionesFuncionario(Integer rut, Integer ident) {
        return anotacionesService.getAnotacionesByRutAndIdent(rut, ident);
    }

    private List<LicenciasMedicasFuncionarioDto> licenciasMedicasFuncionario(Integer rut, Integer ident) {
        return licenciasMedicasService.getLicenciasMedicasByRut(rut, ident);
    }

    private List<CargasFamiliaresDto> cargasFamiliaresFuncionario(Integer rut, Integer ident) {
        return cargasFamilaresService.getCargasFamiliaresByRut(rut, ident);
    }

    private List<ProfesionDto> profesionesFuncionario(Integer rut) {
        return profesionService.getProfesionesByRut(rut);
    }

    private List<CursoFuncionarioDto> cursosFuncionario(Integer rut, Integer ident) {
        return cursoService.getCursoByRut(rut, ident);

    }

    private List<ContratoDto> contratosHistoricosFuncionario(Integer rut) {
        return contratosHistoricosService.getContratosByRut(rut);
    }

}
