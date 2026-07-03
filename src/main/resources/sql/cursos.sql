select
    peestudios.ident,
    peestudios.rut,
    peestudios.linestudios,
    peestudios.fechainiciocurso,
    peestudios.fechaterminocurso,
    peestudios.codtipocurso,
    petiposcurso.desctipocurso,
    peestudios.codinsteducacion,
    'descinsteducacion' = isnull(peinsteducacion.descinsteducacion, ''),
    penivtecinst.nombrenivtec,
    peestudios.codcurso,
    petablacursos.desccurso,
    peestudios.cursohoras,
    peestudios.notacurso,
    peestudios.titulocurso,
    pemateriacurso.materiacurso,
    peestudios.resolestud,
    peestudios.fecharesolestud,
    'puntos' = dbo.fn_puntajecapacitacion(
        peestudios.ident,
        peestudios.codinsteducacion,
        peestudios.codnivtec,
        1,
        case
            when isnumeric(peestudios.notacurso) = 0 then 0
            else convert(numeric(8, 2), peestudios.notacurso)
        end,
        case
            when isnumeric(peestudios.cursohoras) = 0 then 0
            else convert(numeric(6), peestudios.cursohoras)
        end
    ),
    penivtecinst.codnivtec,
    peestudios.valor_curso
From
    peestudios
    left join pemateriacurso on pemateriacurso.ident = peestudios.ident
    and pemateriacurso.rut = peestudios.rut
    and pemateriacurso.linestudios = peestudios.linestudios
    left join petablacursos on petablacursos.ident = peestudios.ident
    and petablacursos.codcurso = peestudios.codcurso
    left join peinsteducacion on peinsteducacion.ident = peestudios.ident
    and peinsteducacion.codinsteducacion = peestudios.codinsteducacion
    left join petiposcurso on petiposcurso.ident = peestudios.ident
    and peestudios.codtipocurso = petiposcurso.codtipocurso
    left join penivtecinst on penivtecinst.codnivtec = peestudios.codnivtec
where
    peestudios.ident = :ident
    and peestudios.rut = :rut
order by
    peestudios.fechainiciocurso desc