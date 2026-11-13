select
    v.rut,
    v.vrut,
    v.nombre,
    v.direccion,
    v.fono,
    v.EMAIL
from
    vcontribuyentes v
where
    v.rut = :rut