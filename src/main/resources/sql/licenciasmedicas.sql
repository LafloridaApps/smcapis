select
    lmlicencias.fechaini,
    lmlicencias.codcaja,
    lmlicencias.codafp,
    lmlicencias.codisapre,
    lmlicencias.FECHARECEPCION,
    lmlicencias.resolucion,
    lmlicencias.numlic,
    lmlicencias.diaslic,
    lmlicencias.codtipolic,
    lmlicencias.codtiporeposo,
    lmlicencias.codlugarreposo,
    lmlicencias.diagnostico,
    lmtipolicencia.desctipolic,
    'numlic_formateado' = dbo.fn_NumLicenciaDescomponeVER(lmlicencias.numlic, lmlicencias.verificadorLM),
    (
        select
            tipomovlm
        From
            lmresoluciones
        Where
            lmresoluciones.ident = lmlicencias.ident
            And NumLic = lmlicencias.NumLic
            and linresollm = (
                select
                    max(r.linresollm)
                from
                    lmresoluciones r
                where
                    r.ident = lmresoluciones.ident
                    and r.numlic = lmresoluciones.numlic
            )
    ) as tipomovlmresol
From
    lmlicencias
    left join lmtipolicencia on lmlicencias.codtipolic = lmtipolicencia.codtipolic
WHERE
    LMLICENCIAS.ident = :ident
    AND LMLICENCIAS.rut = :rut
ORDER BY
    LMLICENCIAS.fechaini DESC