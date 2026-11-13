select
    p.CLAVE,
    p.rol,
    c.DESCRIPCALLE,
    d.DIRNUMERO,
    d.DIRACLARA,
    d.ROL_SII,
    d.VROL_SII,
    n.FECHAINICIONEG,
    n.NOMBRE_FANTASIA,
    p.CON_RUT as rut_rep_legal,
    'nombre_rep_legal' = (
        select
            nombre
        from
            vcontribuyentes v
        where
            v.rut = p.con_rut
    ),
    'direc_rep_legal' = (
        select
            direccion
        from
            vcontribuyentes z
        where
            z.rut = p.con_rut
    ),
p.con2_rut as rut_arriendo,
case when p.con2_rut is not null then (select nombre from vcontribuyentes x where x.rut=p.con2_rut) end nombre_arrendatario,
'clase_alcohol' = (select  descripcion from parnegalc alc
                                       inner join ALCOHOLES a on trim(alc.clase)=trim(a.clase) where alc.idparneg=n.idparneg )
from
    PARNEGOCIO p
    inner join NEGOCIOS n on p.IDPARNEG = n.IDPARNEG
    inner join DIRECCIONES d on p.DIRID = d.DIRID
    inner join calles c on d.CODIGOCALLE = c.CODIGOCALLE
where
    n.FECHATERMINONEG is null
    and p.rut = :rut