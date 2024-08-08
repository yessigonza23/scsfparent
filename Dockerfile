# Usar una imagen base de Java
	FROM eclipse-temurin:17-jre-alpine

	# Establecer el directorio de trabajo
	WORKDIR /app

	# Copiar el archivo JAR al contenedor
	COPY apigateway-0.0.1-SNAPSHOT.jar /app/apigateway-server.jar
        
        EXPOSE 8760
 
	# Comando para ejecutar el JAR
	CMD ["java", "-jar", "apigateway-server.jar"]
