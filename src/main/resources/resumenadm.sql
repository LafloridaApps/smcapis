WITH AbsenceDetails AS (
    SELECT
        a.rut,
        a.ident,
        YEAR(a.fechainicio) AS anio,
        (SELECT TOP 1 z.cantdiasadmin FROM PEINICIALES z WHERE z.rut = a.rut AND z.IDENT = a.ident) AS cantidad_adm,
        CASE
            -- Caso 1: Ausencia de un solo día
            WHEN CAST(a.fechainicio AS DATE) = CAST(a.fechatermino AS DATE) THEN
                CASE 
                    WHEN DATENAME(weekday, a.fechainicio) NOT IN ('Saturday', 'Sunday', 'Sábado', 'Domingo')
                         AND CAST(a.fechainicio AS DATE) NOT IN (SELECT fecha_feriado FROM feriados) THEN
                        CASE
                            WHEN CAST(a.fechatermino AS TIME) = '00:00:00' THEN 1.0
                            WHEN CAST(a.fechatermino AS TIME) IN ('12:00:00', '17:30:00') THEN 0.5
                            ELSE 0.0
                        END
                    ELSE 0.0
                END
            -- Caso 2: Ausencia de varios días
            ELSE
                -- Contar días completos hábiles en el rango de fechas
                (SELECT COUNT(*)
                 FROM (
                     SELECT TOP (DATEDIFF(day, CAST(a.fechainicio AS DATE), CAST(a.fechatermino AS DATE)) + 1)
                     DATEADD(day, ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1, CAST(a.fechainicio AS DATE)) AS AbsenceDate
                     FROM PEAUSENCIAS p
                 ) AS d
                 WHERE DATENAME(weekday, d.AbsenceDate) NOT IN ('Saturday', 'Sunday', 'Sábado', 'Domingo')
                 AND d.AbsenceDate NOT IN (SELECT feriado FROM feriados))
        END AS absence_duration
    FROM PEAUSENCIAS a
    WHERE
        a.rut = :rut
        AND a.ident = :ident
        AND YEAR(a.fechainicio) = YEAR(GETDATE())
        AND a.CODTIPOAUSENCIA = 2
        AND a.fechainicio <= a.fechatermino
)

SELECT
    rut,
    ident,
    anio,
    MAX(cantidad_adm) AS cantidad_adm,
    SUM(absence_duration) AS total_dias_ausencia,
    MAX(cantidad_adm) - SUM(absence_duration) AS saldo
FROM AbsenceDetails
GROUP BY
    rut,
    ident,
    anio;