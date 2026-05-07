select
    CODGRUPOART,
    CODSUBGRUPOART,
    CODRUBROART,
    CODART,
    NOMBREART
from
    adarticulos
where
    CODGRUPOART = :codgrupoart
    and CODSUBGRUPOART = :codsubgrupoart
    and CODRUBROART = :codrubroart
    and CODART = :codart