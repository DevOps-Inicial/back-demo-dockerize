# INDICAMOS QUE IMAGEN USAREMOS DE OPENJDK
FROM openjdk:19-alpine3.16
# OPCIONALMENTE SE PUEDE ADICIONAR UN VOLUMEN
VOLUME /tmp
# EL PUERTO DONDE SE DESPLIEGA LA APLICACION
EXPOSE 9060
# INDICAMOS DONDE SE ENCUENTRA EL ARCHIVO .JAR DFE LA APLICACION
ARG JAR_FILE=build/libs/back-demo-dockerize.jar
# OPCIONALMENTE CAMBIAMOS EL NOMBRE DEL EJECUTABLE
ADD ${JAR_FILE} app.jar
# EJECUTE ESTE ARCHIVO DESDE LA LINEA DE COMANDOS DEL CONTENEDOR
ENTRYPOINT ["java", "-jar", "/app.jar"]