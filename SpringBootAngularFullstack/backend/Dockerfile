FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
ARG SPRING_PROFILES_ACTIVE=dev
RUN ./mvnw clean package -DskipTests -P${SPRING_PROFILES_ACTIVE}

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
ENTRYPOINT ["java","-jar","app.jar"]
