# Stage 1: Build jar (optional, falls schon gebaut, sonst nur Stage 2 nötig)
# FROM eclipse-temurin:21 AS build
# WORKDIR /app
# COPY . .
# RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
