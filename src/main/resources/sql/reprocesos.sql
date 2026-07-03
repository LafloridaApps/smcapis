select r.IDENT ,r.ANOREMUN , r.MESREMUN ,r.DESCRIPCIONPROCESO , r.NROLIQ   from REPROCESOS r
where  r.IDENT = :ident and r.LIQVISIBLEWEB =1 and r.ANOREMUN = :anio and 
exists  (select * from reliquidacion z where z.ident=r.IDENT  and r.NROLIQ =z.linliq  and z.rut= :rut )
order by r.ANOREMUN desc