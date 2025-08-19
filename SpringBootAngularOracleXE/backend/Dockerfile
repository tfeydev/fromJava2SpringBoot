# --- Build Stage ---
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY mvnw . 
COPY .mvn .mvn
COPY pom.xml . 
RUN chmod +x ./mvnw

COPY src ./src

ARG SPRING_PROFILES_ACTIVE=dev
RUN ./mvnw clean package -DskipTests -P${SPRING_PROFILES_ACTIVE}

# --- Runtime Stage ---
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Environment variables
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

ENTRYPOINT ["java", "-jar", "app.jar"]
