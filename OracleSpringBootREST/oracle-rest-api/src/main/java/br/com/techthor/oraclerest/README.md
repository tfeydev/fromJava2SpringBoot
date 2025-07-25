# Oracle REST API with Spring Boot

This project demonstrates how to build a simple REST API using **Spring Boot** and **Oracle Database XE**.  
It connects to the Oracle `HR` schema and exposes data from the `EMPLOYEES` table via a REST endpoint.

## 🔧 Technology Stack

- Java 21
- Spring Boot 3.5.4
- Spring Web
- Spring Data JPA
- Oracle XE (21c+)
- ojdbc11 (JDBC Driver)

## 📦 Maven Coordinates

```xml
<groupId>br.com.techthor</groupId>
<artifactId>oracle-rest-api</artifactId>
<version>0.0.1-SNAPSHOT</version>
```

## ▶️ Running the Application

Make sure Oracle XE is installed, running, and the `HR` schema is available and unlocked:

```sql
ALTER USER hr ACCOUNT UNLOCK;
ALTER USER hr IDENTIFIED BY hr;
```

Then start the application:

```bash
mvn spring-boot:run
```

## 🔗 Oracle Database Configuration

Edit the connection in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=hr
spring.datasource.password=hr
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
```

## 📂 Project Structure

```
src/
 └── main/
     ├── java/br/com/techthor/oraclerest/
     │   ├── controller/        → REST Controllers
     │   ├── entity/            → JPA Entities
     │   └── repository/        → Spring Data Repositories
     └── resources/
         └── application.properties
```

## ✅ Implemented Endpoint

### `GET /api/employees`

Returns all employees from the `HR.EMPLOYEES` table.

Example:

```json
[
  {
    "id": 100,
    "firstName": "Steven",
    "lastName": "King"
  },
  ...
]
```

## 🛠️ CRUD

Create, update and delete endpoints will follow in upcoming versions.
