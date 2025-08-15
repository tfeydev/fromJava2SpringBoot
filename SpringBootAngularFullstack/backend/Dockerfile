# Base image
FROM eclipse-temurin:21-jdk

# Arbeitsverzeichnis
WORKDIR /app

# JAR-Datei kopieren
COPY target/*.jar app.jar

# Port freigeben
EXPOSE 8080

# Startbefehl
ENTRYPOINT ["java", "-jar", "app.jar"]
