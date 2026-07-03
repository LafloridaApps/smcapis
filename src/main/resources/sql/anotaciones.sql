select
    peanotaciones.*,
    DESCRIPSUBGRUPOCALIF,
    DESCTIPOANOTACION
From
    peanotaciones
    left join petiposanotacion on peanotaciones.codtipoanotacion = petiposanotacion.codtipoanotacion
    left join pesubfactorcalif on pesubfactorcalif.ident = peanotaciones.ident
    and pesubfactorcalif.anocalif = peanotaciones.anocalif
    and pesubfactorcalif.numgrupocalif = peanotaciones.numgrupocalif
    and pesubfactorcalif.numsubgrupocalif = peanotaciones.numsubgrupocalif
where
    peanotaciones.rut = :rut
    and peanotaciones.ident = :ident
order by
    peanotaciones.LINANOTACION