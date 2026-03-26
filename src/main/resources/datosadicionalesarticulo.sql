select
    maestro.CODADIC,
    maestro.DESCADIC,
    b.NOMBREADIC
from
    AFMAEINVADIC maestro
    inner join AFDATOSADIC b on maestro.CODADIC = b.CODADIC
where
    maestro.codgrupoart = :codgrupoart
    and maestro.codsubgrupoart = :codsubgrupoart
    and maestro.codrubroart = :codrubroart
    and maestro.codart = :codart
    and maestro.codinv = :codinv