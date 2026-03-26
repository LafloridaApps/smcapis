SELECT
    negocios.clave,
    negocios.rol,
    parnegocio.rut,
    calles.DESCRIPCALLE,
    direcciones.DIRNUMERO,
    direcciones.DIRACLARA,
    direcciones.ROL_SII,
    direcciones.VROL_SII
from
    parnegocio
    inner join negocios on parnegocio.IDPARNEG = negocios.IDPARNEG
    inner join direcciones on parnegocio.DIRID = direcciones.DIRID
    inner join calles on direcciones.CODIGOCALLE = calles.CODIGOCALLE
where
    negocios.clave = 4
    and FECHATERMINONEG is null
    and parnegocio.rut = :rut