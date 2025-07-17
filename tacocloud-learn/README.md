
# TacoCloud-Learn

This project is based on the first three chapters of **Spring in Action (6th Edition)** by Craig Walls.

The goal is to implement the sample project **Taco Cloud** using a modern architecture:
- Spring Boot 3 (Java 21)
- JPA with H2 in-memory database
- React (via Vite) as the future frontend (replacing Thymeleaf)

---

## 🔧 Setup

### 📦 Backend (Spring Boot + H2)

#### 1. Prerequisites

- Java 21  
- Maven  
- IDE (e.g., IntelliJ IDEA)

#### 2. Key Dependencies (from `pom.xml`)

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

The frontend is being developed with React and Vite and will replace the traditional Thymeleaf setup from the book. Integration will take place after completing the backend fundamentals.

---

## 📚 Relation to the Book

This project follows the learning path of *Spring in Action*:

- Chapters 1–3: Spring Boot fundamentals, controllers, and data modeling with JPA  
- Goal: A modern, realistic architecture with REST and frontend decoupling

---

## 📝 Notes

- The project currently has **no security or user management**.  
- Intended as a simple introduction to Spring Boot with database integration.  
- The H2 console is available at `http://localhost:8080/h2-console`.

---

## 📁 Folder Structure

```text
├── model             → JPA entity classes (Taco, Ingredient, TacoOrder)
├── repository        → CrudRepository interfaces
├── service           → Business logic layer
├── controller        → REST endpoints (e.g., /api/orders)
└── config            → Configuration classes (e.g., WebConfig)
```

---

## 📌 Status

✅ Backend is functional  
🚧 Frontend is in progress
