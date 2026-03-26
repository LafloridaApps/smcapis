select
    depto,
    NOMBRE_DEPARTAMENTO
from
    departamentos
where
    unidad_inactiva = 0
    and depto not in (0, 1)