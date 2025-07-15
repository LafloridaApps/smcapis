SELECT 
ROW_NUMBER() OVER (ORDER BY a.fechainicio desc) AS numero,
    resol as resolucion,
	cast(a.fecharesol as date) as fechaResolucion,
    CAST(a.fechainicio AS DATE) AS fechaInicio,
    CAST(a.fechatermino AS DATE) AS fechaTermino,

    CASE 
        WHEN CAST(a.fechainicio AS DATE) = CAST(a.fechatermino AS DATE) THEN
            CASE 
                WHEN CAST(a.fechainicio AS TIME) = '00:00:00' AND CAST(a.fechatermino AS TIME) = '12:00:00' THEN 1.0
                WHEN CAST(a.fechainicio AS TIME) = '00:00:00' OR CAST(a.fechatermino AS TIME) = '12:00:00' THEN 0.5
                ELSE 0.5
            END
        ELSE
            DATEDIFF(DAY, CAST(a.fechainicio AS DATE), CAST(a.fechatermino AS DATE)) 
            + 
            CASE WHEN CAST(a.fechainicio AS TIME) = '00:00:00' THEN 0.5 ELSE 0.0 END
            + 
            CASE WHEN CAST(a.fechatermino AS TIME) = '12:00:00' THEN 0.5 ELSE 0.0 END
    END AS periodo
FROM PEAUSENCIAS a
WHERE 
    a.rut = :rut
    AND a.ident = :ident

    AND a.CODTIPOAUSENCIA = 1
    AND a.fechainicio <= a.fechatermino
ORDER BY a.fechainicio desc


