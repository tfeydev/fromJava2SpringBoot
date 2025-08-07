# Fullstack E-Commerce Application with Spring Boot, Angular, and Oracle

This repository delivers a full-stack e-commerce reference implementation, originally authored by Chad Darby, and now adapted to use **Oracle Database XE** or **Oracle Autonomous Database (ADB)** on the backend and Angular 20+ on the frontend.

---

## 📚 Course Foundation

- **Instructor:** Chad Darby
- **Course:** Full Stack: Angular and Spring Boot (Udemy)
- **Original DB:** MySQL
- **Adaptation:** Oracle XE / Oracle ADB with planned PL/SQL extensions

---

## 🏗 Architecture

| Layer     | Technology                                                   |
|-----------|--------------------------------------------------------------|
| Frontend  | Angular 20+ (Standalone Components, Angular Material)        |
| Backend   | Spring Boot 3.5.4 (REST APIs, Spring Data JPA, OpenAPI)      |
| Database  | Oracle XE / Autonomous DB (Schemas: `EC` for e-commerce)     |
| Data Access | Hibernate ORM (Oracle dialect)                             |

---

## 🔄 Migration Status

| Component          | Status       | Notes                                                                 |
|-------------------|--------------|-----------------------------------------------------------------------|
| EC Schema & Grants | ✅ Completed | EC user created (ADB or XE), granted `CONNECT`, `RESOURCE`, `UNLIMITED TABLESPACE` |
| Table Definitions  | ✅ Completed | All tables migrated from MySQL to Oracle                              |
| Sample Data        | 🔶 In Progress | Product/category inserts in progress                                 |
| PL/SQL Logic       | 🔜 Planned    | Stored procedures, triggers, views                                   |
| Spring Integration | 🔜 Planned    | PL/SQL integration, transaction tuning                                |

---

## 🚀 Getting Started

### 1. Prerequisites

- Oracle XE 21c (locally) or Autonomous DB (OCI)
- Java 21 and Maven 3.8+
- Node.js and npm for Angular frontend

---

### 2. Initialize EC Schema

#### ✅ Option A: Oracle Autonomous Database (ADB – OCI)

1. Create ADB (e.g., "ecommerce") via OCI Console
2. Download and extract **Wallet**
3. Use SQL Developer or SQL*Plus:

```sql
-- Connect as ADMIN
CREATE USER EC IDENTIFIED BY <your_password>;
GRANT CONNECT, RESOURCE, UNLIMITED TABLESPACE TO EC;
```

📝 *Don't forget to configure `tnsnames.ora` using the extracted Wallet.*

#### ✅ Option B: Oracle XE (local)

```bash
sqlplus sys/<SYS_PASSWORD>@localhost:1521/XEPDB1 AS SYSDBA @backend/src/main/resources/sql/02-create-user-and-schema.sql
```

---

### 3. Load Sample Data

```bash
sqlplus ec/ec@localhost:1521/XEPDB1 @backend/src/main/resources/sql/03-insert-sample-data.sql
```

*(Adjust connection string if using ADB.)*

---

### 4. Run Spring Boot Backend

Edit your `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=ec
spring.datasource.password=ec
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
```

Then start the backend:

```bash
cd backend
mvn clean spring-boot:run
```

📄 API Docs available at: [http://localhost:8080/docs](http://localhost:8080/docs)

---

### 5. Run Angular Frontend

```bash
cd angular-frontend
npm install
npm start
```

Then open: [http://localhost:4200](http://localhost:4200)

🖼 Place product images in:  
`angular-frontend/src/assets/images/products`

---

## 📁 Project Structure

```plaintext
├── backend/
│   ├── src/main/java/...           # Spring Boot application
│   ├── src/main/resources/sql/     # SQL setup scripts
│   └── src/main/resources/         # application.properties, OpenAPI config
└── angular-frontend/
    ├── src/app/
    │   ├── core/navigation/        # Standalone nav component
    │   ├── pages/                  # Standalone page components
    │   └── service/                # HttpClient services
    ├── src/assets/                 # Images, logos
    ├── src/custom-theme.scss       # Angular Material theme overrides
    └── src/main.ts                 # Angular app bootstrap
```

---

## 🔮 Next Steps

- Develop PL/SQL procedures (e.g., order processing)
- Add audit triggers and materialized views
- Enhance Spring integration (retry logic, tuning)
- Implement full CRUD with security and unit tests

---

This project follows modern best practices:
- DTO mapping (Java)
- Standalone Angular components
- Server-side pagination
- Angular Material UI
- Lombok for entities
- OpenAPI integration