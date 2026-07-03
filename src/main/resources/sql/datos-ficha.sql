
--profesiones
select PROFESION froM PROFESION
              inner join PROFESIONES on PROFESION.IDPROFESION=PROFESIONES.IDPROFESION
WHERE RUT=22277773

--cargas familiares
select personas.APELLIDOPATERNO,PERSONAS.APELLIDOMATERNO, personas.NOMBRES,
personas.FECHA_NACIMIENTO, FECHAINICIO,FECHATERMINO,FECHARESOL,RESOL,DESCPARENTESCO
from RECARGASFAM
              inner join PEPARENTESCO on RECARGASFAM.CODPARENTESCO=PEPARENTESCO.CODPARENTESCO
              inner join personas on RECARGASFAM.CON_RUT=personas.rut
where RECARGASFAM.rut =22277773 and ident=1


--cursos
select  peestudios.ident, peestudios.rut,  
 peestudios.linestudios,   
 peestudios.fechainiciocurso, peestudios.fechaterminocurso,  
  
 peestudios.codtipocurso, petiposcurso.desctipocurso,  
 peestudios.codinsteducacion, 'descinsteducacion'=isnull(peinsteducacion.descinsteducacion,''),  penivtecinst.nombrenivtec,   
 peestudios.codcurso, petablacursos.desccurso,  
 peestudios.cursohoras, peestudios.notacurso , peestudios.titulocurso, pemateriacurso.materiacurso,   
 peestudios.resolestud, peestudios.fecharesolestud,   
 'puntos'=dbo.fn_puntajecapacitacion(  
  peestudios.ident ,   
  peestudios.codinsteducacion,  
  peestudios.codnivtec,1,   
  case when isnumeric(peestudios.notacurso)=0 then 0 else convert(numeric(8,2),peestudios.notacurso) end ,   
  case when isnumeric(peestudios.cursohoras)=0 then 0 else convert(numeric(6),peestudios.cursohoras) end ),  
 penivtecinst.codnivtec,  
 peestudios.valor_curso  
From peestudios   
left join pemateriacurso on   
 pemateriacurso.ident = peestudios.ident and   
 pemateriacurso.rut = peestudios.rut and   
 pemateriacurso.linestudios = peestudios.linestudios  
left join petablacursos on   
 petablacursos.ident = peestudios.ident and  
 petablacursos.codcurso = peestudios.codcurso  
left join peinsteducacion on   
 peinsteducacion.ident = peestudios.ident and    
 peinsteducacion.codinsteducacion = peestudios.codinsteducacion  
left join petiposcurso on   
 petiposcurso.ident = peestudios.ident and    
 peestudios.codtipocurso = petiposcurso.codtipocurso  
left join penivtecinst on   
 penivtecinst.codnivtec = peestudios.codnivtec  
where peestudios.ident =1 and peestudios.rut = 22277773  
order by peestudios.fechainiciocurso desc  

--anotaciones
 select peanotaciones.*,DESCRIPSUBGRUPOCALIF, DESCTIPOANOTACION From peanotaciones   
  left join petiposanotacion on peanotaciones.codtipoanotacion = petiposanotacion.codtipoanotacion   
  left join pesubfactorcalif on  pesubfactorcalif.ident = peanotaciones.ident and   
      pesubfactorcalif.anocalif = peanotaciones.anocalif and   
      pesubfactorcalif.numgrupocalif = peanotaciones.numgrupocalif and   
      pesubfactorcalif.numsubgrupocalif = peanotaciones.numsubgrupocalif   
  where peanotaciones.rut =22277773 and peanotaciones.ident = 1  
  order by peanotaciones.LINANOTACION  

  --licencias medicas
  select  lmlicencias.fechaini,lmlicencias.codcaja, lmlicencias.codafp,lmlicencias.codisapre, lmlicencias.FECHARECEPCION, lmlicencias.resolucion,lmlicencias.numlic,
  lmlicencias.diaslic,lmlicencias.codtipolic, lmlicencias.codtiporeposo , lmlicencias.codlugarreposo, lmlicencias.diagnostico, lmtipolicencia.desctipolic,
  'numlic_formateado'=dbo.fn_NumLicenciaDescomponeVER(lmlicencias.numlic, lmlicencias.verificadorLM),
  (select tipomovlm         From lmresoluciones         Where lmresoluciones.ident = lmlicencias.ident And NumLic = lmlicencias.NumLic  and
  linresollm = (select max(r.linresollm) from lmresoluciones
  r where r.ident =lmresoluciones.ident and r.numlic = lmresoluciones.numlic)) as tipomovlmresol 
  From lmlicencias left join lmtipolicencia on lmlicencias.codtipolic = lmtipolicencia.codtipolic  
  WHERE LMLICENCIAS.ident = 1 AND LMLICENCIAS.rut = 22277773 ORDER BY LMLICENCIAS.fechaini DESC