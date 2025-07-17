
# TacoCloud-Learn

Dieses Projekt basiert auf den ersten drei Kapiteln von **Spring in Action (6. Auflage)** von Craig Walls.

Ziel ist es, das Beispielprojekt **Taco Cloud** mit moderner Architektur umzusetzen:
- Spring Boot 3 (Java 21)
- JPA mit H2-In-Memory-Datenbank
- React (via Vite) als zukünftiges Frontend (statt Thymeleaf)

---

## 🔧 Einrichtung

### 📦 Backend (Spring Boot + H2)

#### 1. Voraussetzungen

- Java 21
- Maven
- IDE (z. B. IntelliJ IDEA)

#### 2. Wichtige Abhängigkeiten (aus `pom.xml`)

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>

<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.18.32</version>
  <scope>provided</scope>
</dependency>
```

#### 3. `application.properties`

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

---

## 🚧 Frontend (React/Vite)

Das Frontend wird mit React und Vite entwickelt und ersetzt das klassische Thymeleaf aus dem Buch. Die Integration erfolgt nach Abschluss der Backend-Grundlagen.

---

## 📚 Bezug zum Buch

Dieses Projekt folgt dem Lernpfad von *Spring in Action*:

- Kapitel 1–3: Grundlagen von Spring Boot, Controller, Datenmodellierung mit JPA
- Ziel: Moderne, realitätsnahe Architektur mit REST und Frontend-Entkopplung

---

## 📝 Hinweise

- Aktuell ist das Projekt **ohne Sicherheits- oder Benutzermanagement**.
- Für einen einfachen Einstieg in Spring Boot mit Datenbankanbindung gedacht.
- Die H2-Konsole ist unter `http://localhost:8080/h2-console` erreichbar.

---

## 📁 Ordnerstruktur

```text
├── model             → JPA-Entity-Klassen (Taco, Ingredient, TacoOrder)
├── repository        → CrudRepository-Interfaces
├── service           → Geschäftslogik-Schicht
├── controller        → REST-Endpunkte (z. B. /api/orders)
└── config            → Konfigurationen (z. B. WebConfig)
```

---

## 📌 Status

✅ Backend funktionsfähig  
🚧 Frontend in Arbeit
