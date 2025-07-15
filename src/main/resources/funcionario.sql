SELECT personas.rut, personas.NOMBRES,PERSONAS.APELLIDOPATERNO,personas.APELLIDOMATERNO, (select vrut from CONTRIBUYENTES c where c.RUT = PERSONAS.rut) as vrut,
(select email from CONTRIBUYENTES where CONTRIBUYENTES.rut=personas.rut) as email, FOTO,
 contratomes.DEPTO as coddepto, 
 (select nombre_departamento from DEPARTAMENTOS d where d.depto=contratomes.DEPTO) as departamento,
 contratomes.ident
                FROM RECONTRATOS AS contratos  
                INNER JOIN RECONTRATOMES contratomes  
                    ON contratos.IDENT = contratomes.IDENT  
                   AND contratos.rut = contratomes.RUT  
                   AND contratos.LINCONTRATO = contratomes.LINCONTRATO  
                LEFT JOIN RECONTESCALA escala  
                    ON contratos.IDENT = escala.IDENT  
                   AND contratos.RUT = escala.RUT  
                   AND contratomes.ANOREMUN = escala.ANOREMUN  
                   AND contratomes.MESREMUN = escala.MESREMUN  
                   AND contratomes.LINCONTRATO = escala.LINCONTRATO  
				inner join PERSONAS on contratos.rut=PERSONAS.rut
				left join PEFOTOGRAFIA on contratos.rut=PEFOTOGRAFIA.rut
                WHERE contratomes.ANOREMUN = YEAR(GETDATE())  
                 AND contratomes.MESREMUN = (select max(r.MESREMUN) from recontratomes r where  r.rut=contratomes.rut  and r.LINCONTRATO=contratomes.LINCONTRATO and r.ANOREMUN=YEAR(GETDATE() ))

                  AND contratos.FECHAINI <= CONVERT(date, GETDATE(), 104)  
                  AND (contratos.fechafin IS NULL OR contratos.fechafin >= CONVERT(date, GETDATE(), 104))  
                  AND contratos.RUT = :rut
