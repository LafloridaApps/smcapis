SELECT
    (
        select
            nombre_departamento
        from
            DEPARTAMENTOS d
        where
            d.depto = contratomes.DEPTO
    ) as departamento,
    contratomes.ident,
    (
        select
            nombretipocontrato
        from
            RETIPOSCONTRATO tipos
        where
            tipos.IDENT = contratomes.IDENT
            and tipos.CODTIPOCONTRATO = contratomes.CODTIPOCONTRATO
    ) as tipocontrato,
    case
        when escala.grado is null then 0
        else grado
    end grado,
    nombreescalafon,
    contratos.fechaini,
    contratos.fechafin,
    contratos.FECHARESOLCONTR,
    contratos.NUMRESOLCONTR,
    contratos.OBSCONTRE
FROM
    RECONTRATOS AS contratos
    INNER JOIN RECONTRATOMES contratomes ON contratos.IDENT = contratomes.IDENT
    AND contratos.rut = contratomes.RUT
    AND contratos.LINCONTRATO = contratomes.LINCONTRATO
    LEFT JOIN RECONTESCALA escala ON contratos.IDENT = escala.IDENT
    AND contratos.RUT = escala.RUT
    AND contratomes.ANOREMUN = escala.ANOREMUN
    AND contratomes.MESREMUN = escala.MESREMUN
    AND contratomes.LINCONTRATO = escala.LINCONTRATO
    inner join PERSONAS on contratos.rut = PERSONAS.rut
    left join reescalafones on contratomes.codescalafon = reescalafones.codescalafon
    and contratomes.ident = reescalafones.ident
    left join PEFOTOGRAFIA on contratos.rut = PEFOTOGRAFIA.rut
WHERE
    contratomes.ANOREMUN = YEAR(GETDATE())
    AND contratomes.MESREMUN = (
        select
            max(r.MESREMUN)
        from
            recontratomes r
        where
            r.rut = contratomes.rut
            and r.LINCONTRATO = contratomes.LINCONTRATO
            and r.ANOREMUN = YEAR(GETDATE())
    )
    AND contratos.FECHAINI < CONVERT(date, GETDATE(), 104)
    and contratos.fechafin < convert(date, getdate(), 104)
    AND contratos.RUT = :rut