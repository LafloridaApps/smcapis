select
    a.fotoarticulo
from
    AFMAEINVFOTO a
where
    a.codgrupoart = :codgrupoart
    and a.codsubgrupoart = :codsubgrupoart
    and a.codrubroart = codrubroart
    and a.codart = :codart
    and a.codinv = :correlativo