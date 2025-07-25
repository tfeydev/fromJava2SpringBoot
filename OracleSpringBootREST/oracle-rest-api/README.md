
# Oracle REST API with Spring Boot

This project demonstrates how to build a simple REST API using **Spring Boot** and **Oracle Database XE**.  
It connects to the Oracle `HR` schema and exposes data from the `EMPLOYEES` table via REST endpoints.

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

Ensure Oracle XE is installed, running, and the `HR` schema is unlocked:

```sql
ALTER USER hr ACCOUNT UNLOCK;
ALTER USER hr IDENTIFIED BY hr;
```

Then start the Spring Boot application:

```bash
mvn spring-boot:run
```

## 🔗 Database Configuration

Edit the connection details in `src/main/resources/application.properties`:

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

## ✅ Implemented Endpoints

### `GET /api/employees`

Returns all employees from the `HR.EMPLOYEES` table.

### `GET /api/employees/{id}`

Returns a single employee by ID.

Example response:

```json
{
  "id": 100,
  "firstName": "Steven",
  "lastName": "King"
}
```

## 📌 Notes

- Oracle XE must have the HR sample schema installed and available.
- All endpoints return JSON.
- More CRUD functionality (POST, PUT, DELETE) will follow in future versions.


## 🔄 Multiple Oracle Schemas: EC + HR

This project uses two Oracle schemas:

- `EC`: main application schema (products, orders, etc.)
- `HR`: secondary schema (employee demo)

### Configuration
- `EC` is the default Spring Boot datasource.
- `HR` is configured via a separate `DataSource`, `EntityManagerFactory`, and `TransactionManager`.

See: `HrDataSourceConfig.java`
