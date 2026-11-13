select
    p.CLAVE,
    p.rol,
    alc.CLASE
from
    PARNEGOCIO p
    inner join NEGOCIOS n on p.IDPARNEG = n.IDPARNEG
    inner join PARNEGALC alc on n.IDPARNEG = alc.IDPARNEG
where
    p.rut = :rut