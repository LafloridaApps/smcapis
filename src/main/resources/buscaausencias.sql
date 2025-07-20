SELECT rut, p.DESCTIPOAUSENCIA, a.FECHAINICIO, a.FECHATERMINO, a.ident
FROM peausencias a
inner join PETIPOSAUSENCIA p  
     on p.CODTIPOAUSENCIA =a.CODTIPOAUSENCIA 
WHERE rut = :rut
  AND ident = :ident
  AND fechainicio <= :fechaFin
  AND fechatermino >= :fechaInicio

  union all
  
  select rut,'LICENCIA MEDICA' desctipoausencia ,  FECHAINI fechainicio , DATEADD(d,DIASLIC ,FECHAINI),ident fechatemrino 
  from LMLICENCIAS 
  where rut=:rut and ident = :ident and FECHAINI  between :fechaInicio and :fechaFin