select
    personas.APELLIDOPATERNO,
    PERSONAS.APELLIDOMATERNO,
    personas.NOMBRES,
    personas.FECHA_NACIMIENTO,
    FECHAINICIO,
    FECHATERMINO,
    FECHARESOL,
    RESOL,
    DESCPARENTESCO
from
    RECARGASFAM
    inner join PEPARENTESCO on RECARGASFAM.CODPARENTESCO = PEPARENTESCO.CODPARENTESCO
    inner join personas on RECARGASFAM.CON_RUT = personas.rut
where
    RECARGASFAM.rut = :rut
    and ident = :ident