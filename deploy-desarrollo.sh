#!/usr/bin/env bash
# Envía el código actual y construye/despliega en el servidor de desarrollo.
set -Eeuo pipefail

ROOT=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)
REMOTE=desarrollo@192.168.10.45
REMOTE_DIR=/home/desarrollo/docker-apis/smcapis
VERSION="$(date -u +%Y%m%dT%H%M%SZ)-$(git -C "$ROOT" rev-parse --short HEAD)"
IMAGE="smcapis:desarrollo-${VERSION}"
ARCHIVE="smcapis-source-${VERSION}.tar.gz"
SSH_OPTIONS=(-o StrictHostKeyChecking=ask)

for command in ssh scp tar sha256sum; do
    command -v "$command" >/dev/null || { echo "Falta instalar: $command" >&2; exit 1; }
done

echo "Verificando Docker y el .env remoto en $REMOTE..."
ssh "${SSH_OPTIONS[@]}" "$REMOTE" \
    "set -eu; command -v curl >/dev/null; command -v tar >/dev/null; command -v sha256sum >/dev/null; docker info >/dev/null; test -r '$REMOTE_DIR/.env'"

echo 'Empaquetando código actual para construir en el servidor...'
TMP_DIR=$(mktemp -d)
trap 'rm -rf -- "$TMP_DIR"' EXIT
tar -czf "$TMP_DIR/$ARCHIVE" -C "$ROOT" \
    Dockerfile .dockerignore .mvn mvnw pom.xml src deploy-runtime.sh
(cd "$TMP_DIR" && sha256sum "$ARCHIVE" > "$ARCHIVE.sha256")

echo 'Transfiriendo código fuente...'
ssh "${SSH_OPTIONS[@]}" "$REMOTE" "mkdir -p '$REMOTE_DIR/releases/$VERSION'"
scp "${SSH_OPTIONS[@]}" "$TMP_DIR/$ARCHIVE" "$TMP_DIR/$ARCHIVE.sha256" \
    "$REMOTE:$REMOTE_DIR/releases/$VERSION/"

echo "Construyendo $IMAGE y activando contenedor en $REMOTE..."
ssh "${SSH_OPTIONS[@]}" "$REMOTE" "bash -se" <<EOF
set -Eeuo pipefail
cd '$REMOTE_DIR/releases/$VERSION'
sha256sum -c '$ARCHIVE.sha256'
mkdir source
tar -xzf '$ARCHIVE' -C source
docker build --pull -t '$IMAGE' ./source
bash ./source/deploy-runtime.sh '$IMAGE' '$REMOTE_DIR/.env'
docker tag '$IMAGE' smcapis:latest
EOF

echo "Despliegue completado: $IMAGE en http://192.168.10.45:8083"
