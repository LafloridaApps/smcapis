SELECT 
    a.rut,
    a.ident,
    YEAR(a.fechainicio) AS anio,
    (SELECT TOP 1 z.cantdiasadmin 
     FROM PEINICIALES z 
     WHERE z.rut = a.rut AND z.IDENT = a.ident) AS cantidad_adm,

    SUM(
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
        END
    ) AS total_dias_ausencia,

    -- Saldo: cantidad disponible - usados
    (SELECT TOP 1 z.cantdiasadmin 
     FROM PEINICIALES z 
     WHERE z.rut = a.rut AND z.IDENT = a.ident)
    -
    SUM(
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
        END
    ) AS saldo
FROM PEAUSENCIAS a
WHERE 
    a.rut = :rut
    AND a.ident = :ident
    AND YEAR(a.fechainicio) = YEAR(GETDATE())
    AND a.CODTIPOAUSENCIA = 2
    AND a.fechainicio <= a.fechatermino
GROUP BY 
    a.rut, 
    a.ident, 
    YEAR(a.fechainicio)
