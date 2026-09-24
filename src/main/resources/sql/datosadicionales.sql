select
    maestro.CODADIC,
    maestro.DESCADIC,
    b.NOMBREADIC
from
    AFMAEINVADIC maestro
    inner join AFDATOSADIC b on maestro.CODADIC = b.CODADIC
where
     maestro.codinv = :codinv