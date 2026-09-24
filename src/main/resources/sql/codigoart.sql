select
    CODGRUPOART,
    CODSUBGRUPOART,
    CODRUBROART,
    CODART,
    NOMBREART
from
    adarticulos
where
    idadquis = 1
    and CODGRUPOART = :codgrupoart
    and CODSUBGRUPOART = :codsubgrupoart
    and CODRUBROART = :codrubroart
    and CODART = :codart