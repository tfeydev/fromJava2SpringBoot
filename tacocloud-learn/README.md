# Tacocloud-Learn Projekt

Dieses Projekt basiert auf den ersten drei Kapiteln von *Spring in Action* (6. Auflage) und ersetzt Thymeleaf durch React/Vite für das Frontend.

## Einrichtung

### Backend
- **Spring Boot mit H2-Datenbank**:
  - Abhängigkeit in `pom.xml` hinzufügen:
    ```xml
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```
  - H2-Datenbank in `application.properties` konfigurieren:
    ```properties
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    ```

### Frontend
- Das Frontend wird mit React und Vite entwickelt (Details folgen nach Backend-Entwicklung).

## Anmerkungen
- Das Projekt wurde neu gestartet, um die Komplexität zu reduzieren und sich strikt an die Anweisungen von *Spring in Action* zu halten.
- Weitere Informationen zur Integration von React/Vite werden nach Abschluss des Backends hinzugefügt.
