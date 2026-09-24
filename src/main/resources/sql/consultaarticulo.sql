select
    maestro.coddocaf,
    maestro.numerodocaf,
    maestro.anodocaf,
    altas.rut,
    altas.NROORDEN,
    altas.FECHAORDEN,
    altas.NROFACTURA,
    altas.FECHAFACTURA,
    maestro.codgrupoart,
    maestro.codsubgrupoart,
    maestro.codrubroart,
    maestro.codart,
    maestro.codinv,
    maestro.vutilinv,
    maestro.valorinv,
    doc.fechadocaf,
    doc.depto,
    deptos.nombre_departamento,
    deptos.jefe_departamento,
    o.nombreoficina,
    o.responsableoficina,
    o.cargooficina,
    art.nombreart,
    maestrodesc.DESCMAEINV
from
    AFALTAS altas
    inner join AFMAEINV maestro on altas.IDADQUIS = maestro.IDADQUIS
    and altas.ANODOCAF = maestro.ANODOCAF
    and altas.CODDOCAF = maestro.CODDOCAF
    and altas.NUMERODOCAF = maestro.NUMERODOCAF
    LEFT  join AFMAEINVDESC maestrodesc on maestrodesc.idadquis=maestro.IDADQUIS
    and maestrodesc.CODGRUPOART =maestro.CODGRUPOART  and maestrodesc.CODSUBGRUPOART =maestro.CODSUBGRUPOART 
    and maestrodesc.CODRUBROART =maestro.CODRUBROART  and maestrodesc.CODART =maestro.CODART  and maestrodesc.CODINV =maestro.CODINV 
    inner join AFDOCAF doc on altas.idadquis = doc.idadquis
    and altas.anodocaf = doc.anodocaf
    and altas.coddocaf = doc.coddocaf
    and altas.numerodocaf = doc.numerodocaf
    and altas.numerodocaf = doc.numerodocaf
    inner join DEPARTAMENTOS deptos on doc.DEPTO = deptos.DEPTO
    left join AFOFICINAS o ON doc.LINOFICINA = o.LINOFICINA
    and deptos.depto = o.depto
    inner join adarticulos art on art.idadquis = maestro.idadquis
    and art.codgrupoart = maestro.codgrupoart
    and art.codsubgrupoart = maestro.codsubgrupoart
    and art.codrubroart = maestro.codrubroart
    and art.codart = maestro.codart
where
    altas.IDADQUIS = 1
    and maestro.codinv = :codinv