#!/bin/bash

# Ruta de Tomcat (ajusta si tu Tomcat está en otra carpeta)
TOMCAT_WEBAPPS="C:/Tomcat9.0/webapps"
WAR_NAME="business-assistant-project-1.0-SNAPSHOT.war"
PROJECT_PATH="C:/workspace/SENA/business-assistant-supermarket-project/business-assistant-project"
TARGET_PATH="$PROJECT_PATH/target/$WAR_NAME"

# 0. Ir al directorio del proyecto
cd "$PROJECT_PATH" || { echo "❌ No se pudo acceder al proyecto"; exit 1; }

# 1. Limpiar y compilar el proyecto
echo "🔧 Ejecutando mvn clean package..."
mvn clean package
if [ $? -ne 0 ]; then
    echo "❌ Falló el build de Maven. Abortando despliegue."
    exit 1
fi

# 2. Eliminar WAR y carpeta descomprimida previos
echo "🧹 Eliminando WAR y carpeta descomprimida anteriores..."
rm -rf "$TOMCAT_WEBAPPS/$WAR_NAME"
rm -rf "$TOMCAT_WEBAPPS/business-assistant-project"

# 3. Copiar nuevo WAR desde target/
echo "📦 Moviendo nuevo WAR a Tomcat webapps..."
mv "$TARGET_PATH" "$TOMCAT_WEBAPPS/"

# 4. Confirmación final
echo "✅ Despliegue completo. Reinicia Tomcat para aplicar los cambios."

 tail -f C:/Tomcat9.0/logs/catalina.2025-06-29.log

exit 0
