# Despliegue de SMC APIs

Ejecutar únicamente por instrucción explícita del usuario («sube smcapis a desarrollo»).

## Desarrollo

- Proyecto backend: `/home/mirko/Documentos/Fuentes/smcapis`.
- Comando: `./deploy.sh desarrollo`.
- Destino SSH: `desarrollo@desarrollo`; `/etc/hosts` resuelve `desarrollo` a `192.168.10.45`.
- Carpeta confirmada en el servidor: `/home/desarrollo/docker-apis/smcapis`.
- Construcción: Dockerfile multietapa con Java 21 y Maven. Actualmente omite los tests Maven.
- Primero se construye y arranca el contenedor local usando `docker.env`.
- Se exporta la imagen mediante `docker save`, se copia el archivo `.tar` y su SHA-256 por SCP, se verifica y se carga con `docker load`.
- El contenedor remoto usa el `.env` existente en el servidor. No sustituirlo por el local.
- Contenedor: `smcapis-container`; puerto `8083`; red `laflorida`; reinicio `always`.
- Se comprueba `/actuator/health`. Si falla el arranque, se intenta restaurar el contenedor anterior.
- Se conservan el contenedor anterior detenido y el archivo transferido para recuperación. No efectuar limpiezas globales de Docker.
- Las contraseñas no se guardan en scripts ni en esta documentación; SSH las solicita si no hay clave autorizada.
- `./deploy.sh local` (o sin argumentos) construye y activa solo localmente.

## Producción

Pendiente de documentar y validar con el usuario. El script rechaza este destino.

## Frontend

El usuario indicó compilar y copiar el contenido de `dist` al `public_html` correspondiente al proyecto. La ruta de ejemplo es `/home/web/www/app.laflorida.cl/public_html/`; no usarla para otro proyecto sin confirmar su correspondencia. Este despliegue de SMC APIs incluye solo backend.
