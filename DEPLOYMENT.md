# Despliegue de SMC APIs

Ejecutar únicamente por instrucción explícita del usuario («sube smcapis a desarrollo»).

## Desarrollo

- Script dedicado al servidor `192.168.10.45`: `./deploy-desarrollo.sh`.
  Envía el código actual (incluidos cambios sin commit) con verificación SHA-256,
  construye la imagen en el servidor con una etiqueta única y ejecuta
  `deploy-runtime.sh` usando el `.env` remoto. El paquete incluye únicamente
  los archivos de construcción, `src` y el script de activación; excluye el
  `.env` local, `target` y el historial Git.
  Al superar la comprobación de salud, actualiza `smcapis:latest` en el servidor.
  No requiere Docker local. Requiere Docker, curl, tar y sha256sum remotos.
  La construcción usa el Dockerfile multietapa y omite los tests Maven.
  SSH solicita la
  contraseña del usuario `desarrollo` si no hay autenticación por clave.
  No inicia contenedores locales. Conserva los archivos y el contenedor anterior.
- Las indicaciones siguientes describen el flujo anterior; para este destino
  se debe utilizar el script dedicado indicado arriba.
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
