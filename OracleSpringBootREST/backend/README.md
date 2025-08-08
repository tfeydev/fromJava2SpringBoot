# Fullstack E-Commerce Application with Spring Boot, Angular, and Oracle ADB

This repository delivers a full-stack e-commerce reference implementation, originally authored by Chad Darby, now adapted to run on **Oracle Autonomous Database (ADB)** on OCI instead of MySQL. The project evolves step-by-step in alignment with Darby’s original course content, modernized for Oracle and cloud deployment.

---

## 📚 Course Foundation

- **Instructor:** Chad Darby
- **Course:** Full Stack: Angular and Spring Boot (Udemy)
- **Original DB:** MySQL
- **Adaptation:** Oracle ADB (OCI) with future PL/SQL extensions and Flyway migration support

---

## 🏗 Architecture

| Layer        | Technology                                                   |
|--------------|--------------------------------------------------------------|
| Frontend     | Angular 20+ (Standalone Components, Angular Material)        |
| Backend      | Spring Boot 3.5.4 (REST APIs, Spring Data JPA, OpenAPI)      |
| Database     | Oracle Autonomous DB (Schema: `EC`)                          |
| Data Access  | Hibernate ORM (Oracle dialect), future Flyway integration    |

---

## 🔄 Migration & Modernization Status

| Component           | Status       | Notes                                                              |
|--------------------|--------------|--------------------------------------------------------------------|
| EC Schema & Grants | ✅ Completed | EC user created on ADB with appropriate privileges                 |
| Table Definitions   | ✅ Completed | Fully migrated from MySQL to Oracle                                |
| Sample Data         | ✅ Completed | Product/Categories                                                 |
| PL/SQL Logic        | 🔜 Planned    | Stored procedures, triggers, views                                 |
| Spring Integration  | ✅ Completed | Oracle ADB integration using Wallet & JDBC (TNS_ADMIN)             |
| Flyway Migration    | 🔜 Planned    | Will provide optional Flyway-based migration alongside SQL scripts |

---

## 🚀 Getting Started

### 1. Prerequisites

- Oracle Autonomous Database (OCI)
- Java 21 and Maven 3.8+
- Node.js and npm for Angular frontend
- Oracle Wallet downloaded and extracted

---

### 2. Initialize EC Schema (ADB)

```sql
-- Connect as ADMIN on ADB
CREATE USER EC IDENTIFIED BY <your_password>;
GRANT CONNECT, RESOURCE, UNLIMITED TABLESPACE TO EC;
```

Ensure `tnsnames.ora` from the Wallet is configured and accessible.

---

### 3. Load Sample Data

```bash
sqlplus ec/ec@<ADB_TNS_ALIAS> @backend/src/main/resources/sql/03-insert-sample-data.sql
```

---

### 4. Run Spring Boot Backend

#### ✅ Oracle ADB Configuration (`application.properties`):

```properties
spring.datasource.url=jdbc:oracle:thin:@<TNS_ALIAS>?TNS_ADMIN=/home/<user>/oracle_wallets/ecommerce
spring.datasource.username=ec
spring.datasource.password=<your_password>
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
```

> Replace `<TNS_ALIAS>` with the entry from your `tnsnames.ora`, e.g., `adb_ecommerce_high`.

Start the backend:

```bash
cd backend
mvn clean spring-boot:run
```

📄 API Docs: [http://localhost:8080/docs](http://localhost:8080/docs)

---

### 5. Run Angular Frontend

```bash
cd angular-frontend
npm install
npm start
```

Open: [http://localhost:4200](http://localhost:4200)

🖼 Product images directory:  
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

## 🔮 Roadmap

- [ ] Integrate Flyway (optional, alongside SQL scripts)
- [ ] Develop PL/SQL logic (order processing, audit)
- [ ] Add materialized views and triggers
- [ ] Enhance Spring retry logic & performance tuning
- [ ] Security & full CRUD with unit tests
- [ ] Track progress against Darby’s original curriculum

---

## ✅ Highlights

- Uses Oracle Autonomous DB (ADB) as default backend
- Wallet-based secure connection (JDBC with `TNS_ADMIN`)
- Fully cloud-ready stack: Spring Boot, Angular, Oracle ADB
- Clean architecture, modular structure
- DTO mapping, pagination, OpenAPI docs, Lombok support

---

*This project is continuously modernized while preserving the educational flow of Chad Darby’s original course.*

---

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

Open Swagger UI at `http://localhost:8080/docs`.
