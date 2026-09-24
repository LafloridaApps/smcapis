#!/usr/bin/env bash
# Activa una imagen ya construida; conserva el contenedor anterior para recuperación.
set -Eeuo pipefail
IMAGE=${1:?Falta imagen}
ENV_FILE=${2:?Falta archivo de entorno}
NAME=smcapis-container
BACKUP="${NAME}-backup-$(date -u +%Y%m%dT%H%M%SZ)"
HAD_OLD=0
NEW_ATTEMPT=0
[[ -r "$ENV_FILE" ]] || { echo 'No se puede leer el archivo de entorno' >&2; exit 1; }
command -v curl >/dev/null
docker image inspect "$IMAGE" >/dev/null
docker network inspect laflorida >/dev/null 2>&1 || docker network create laflorida
rollback() {
    trap - ERR INT TERM
    if (( NEW_ATTEMPT )); then docker rm -f "$NAME" >/dev/null 2>&1 || true; fi
    if (( HAD_OLD )); then
        docker rename "$BACKUP" "$NAME"
        docker start "$NAME"
        echo 'Se restauró el contenedor anterior.' >&2
    fi
    exit 1
}
trap rollback ERR INT TERM
if docker container inspect "$NAME" >/dev/null 2>&1; then
    docker rename "$NAME" "$BACKUP"
    HAD_OLD=1
    docker stop "$BACKUP" >/dev/null
fi
NEW_ATTEMPT=1
docker run --restart always -d -p 8083:8083 --env-file "$ENV_FILE" \
    --network laflorida --add-host=host.docker.internal:host-gateway \
    --name "$NAME" "$IMAGE"
for ((i=0; i<60; i++)); do
    if curl --fail --silent --max-time 5 http://localhost:8083/actuator/health | grep -Eq '"status"[[:space:]]*:[[:space:]]*"UP"'; then
        trap - ERR INT TERM
        echo "Salud UP: $IMAGE"
        if (( HAD_OLD )); then echo "Contenedor anterior conservado: $BACKUP"; fi
        exit 0
    fi
    sleep 2
done
echo 'La aplicación no superó la comprobación de salud.' >&2
rollback
