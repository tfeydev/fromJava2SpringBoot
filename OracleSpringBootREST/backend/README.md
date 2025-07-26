# Oracle E-Commerce Schema Setup

This script bootstraps the `EC` schema for the e-commerce application.

1. **Requirements**

   - Oracle XE 21c+
   - `XEPDB1` PDB
   - `SYSDBA` access

2. **Create Schema & Tables**

```bash
sqlplus sys/<SYS_PASSWORD>@localhost:1521/XEPDB1 AS SYSDBA @02-create-user-and-schema.sql
```

3. **Load Data**

```bash
sqlplus ec/ec@localhost:1521/XEPDB1 @03-insert-sample-data.sql
```

4. **Verify** Connect as `ec` and run:

```sql
SELECT * FROM product_category;
SELECT * FROM product;
```

---

# Oracle REST API (HR Schema)

Demonstrates a Spring Boot REST service exposing the `HR.EMPLOYEES` table.

## 📦 Dependencies

- Spring Boot Starter Web
- Spring Data JPA
- ojdbc11
- SpringDoc OpenAPI UI

## ⚙ Configuration

Edit `src/main/resources/application.properties`:

```properties
# HR (Primary DataSource)
spring.datasource.jdbc-url=jdbc:oracle:thin:@192.168.1.32:1521/XEPDB1
spring.datasource.username=ec
spring.datasource.password=ec
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect

# HR (Secondary DataSource - Read-Only)
spring.hr.datasource.jdbc-url=jdbc:oracle:thin:@192.168.1.32:1521/XEPDB1
spring.hr.datasource.username=hr
spring.hr.datasource.password=hr
spring.hr.datasource.driver-class-name=oracle.jdbc.OracleDriver

# swagger
springdoc.swagger-ui.path=/docs
```

## 🚀 Available Endpoints

- **GET** `/api/employees?page={n}&size={m}` — paginated list
- **GET** `/api/employees/{id}` — single employee by ID

## 🧪 Run

```bash
mvn clean spring-boot:run
```

Open Swagger UI at `http://localhost:8080/docs`.
