#!/bin/bash

# =========================================================
# CONFIGURACIÓN DEL MICROSERVICIO (Actualizado a GHCR)
# =========================================================
NOMBRE_APP="smcapis"
PUERTO="8083"
# Cambiamos la ruta de Docker Hub a GitHub Container Registry
IMAGEN_HUB="ghcr.io/lafloridaapps/smcapis"
# =========================================================


echo "--- Creando imagen docker ($NOMBRE_APP) ---"
./mvnw clean package -DskipTests
docker build -t $NOMBRE_APP:local .
TARGET_IMAGE="$NOMBRE_APP:local"
        
# Limpia imágenes antiguas para no llenar el disco del PC de la oficina
docker image prune -f
echo "--- Proceso Terminado ($OPCION) ---"
