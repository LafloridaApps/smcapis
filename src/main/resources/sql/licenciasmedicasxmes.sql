SELECT
    lmlicencias.fechaini,
    DATEADD(day, DIASLIC -1, lmlicencias.FECHAINI) AS termino,
    lmlicencias.ident,
    lmlicencias.rut
FROM
    lmlicencias
    INNER JOIN refuncionarios ON refuncionarios.IDENT = LMLICENCIAS.IDENT
    AND refuncionarios.RUT = LMLICENCIAS.RUT
    INNER JOIN RECONTRATOS ON recontratos.ident = refuncionarios.ident
    AND recontratos.RUT = REFUNCIONARIOS.RUT
    inner join RECONTRATOMES on recontratos.IDENT = recontratomes.IDENT
    and recontratomes.LINCONTRATO = recontratos.LINCONTRATO
    and recontratos.rut = recontratomes.RUT
WHERE
    lmlicencias.ident = 1
    AND (
        recontratos.FECHAFIN IS NULL
        OR recontratos.FECHAFIN >= GETDATE()
    )
    AND (
        lmlicencias.FECHAINI <= :fechaFin
        AND DATEADD(day, DIASLIC, lmlicencias.FECHAINI) >= :fechaIni
    )
    and RECONTRATOMES.ANOREMUN = year(getdate())
    and recontratomes.MESREMUN = (
        select
            max(mesremun)
        from
            RECONTRATOMES z
        where
            recontratomes.IDENT = z.IDENT
            and z.LINCONTRATO = recontratomes.LINCONTRATO
            and z.ANOREMUN = year(getdate())
            and z.RUT = recontratomes.RUT
    )
    and RECONTRATOMES.DEPTO in (:deptos)
ORDER BY
    lmlicencias.FECHAIni