# Fullstack E-Commerce Application with Spring Boot, Angular, and Oracle Cloud

This repository is a customized version of Chad Darby’s **Full Stack: Angular and Spring Boot** course project (Sections 1–11 completed), adapted to use **Oracle Autonomous Database (ADB)** and **OCI Object Storage** for product images.  
Section 12 development will start next.

--- 

## 📚 Course & Adaptation

| Item         | Details                                                                 |
|--------------|-------------------------------------------------------------------------|
| Instructor   | Chad Darby                                                              |
| Course       | Full Stack: Angular and Spring Boot (Udemy)                             |
| Original DB  | MySQL                                                                   |
| Adaptation   | Oracle XE / Oracle ADB backend + OCI Object Storage for images          |
| Enhancements | Planned PL/SQL logic, triggers, and materialized views                  |

---

## 🏗 Architecture

| Layer      | Technology                                                            |
|------------|------------------------------------------------------------------------|
| Frontend   | Angular 20+ (Standalone Components, Angular Material)                  |
| Backend    | Spring Boot 3.5.4 (REST APIs, Spring Data JPA, OpenAPI/Swagger)         |
| Database   | Oracle XE / Oracle ADB (Schema `EC` for e-commerce)                     |
| Data Layer | Hibernate ORM with Oracle dialect                                      |
| Storage    | OCI Object Storage Bucket for product images                           |

---

## 🔄 Migration Progress

| Component            | Status       | Notes                                                                            |
|----------------------|--------------|----------------------------------------------------------------------------------|
| EC Schema & Grants   | ✅ Completed | User `EC` created with required roles and privileges                             |
| Table Definitions    | ✅ Completed | All MySQL tables migrated to Oracle                                              |
| Sample Data          | ✅ In Completed | Product/category                                                  |
| OCI Object Storage   | ✅ Completed | Bucket configured and integrated for image hosting                               |
| PL/SQL Logic         | 🔜 Planned   | Stored procedures, triggers, and views                                           |
| Spring Integration   | 🔜 Planned   | PL/SQL integration and performance tuning                                        |

---

## 🚀 Getting Started

### 1. Prerequisites

- Oracle XE 21c (local) or Autonomous DB (OCI)
- Java 21, Maven 3.8+
- Node.js & npm for Angular
- OCI account with Object Storage bucket

---

### 2. Database Setup

#### Option A – Oracle Autonomous Database (ADB)

1. Create ADB instance (e.g., "ecommerce") in OCI Console  
2. Download and extract **Wallet**
3. Connect as `ADMIN`:

```sql
CREATE USER EC IDENTIFIED BY <password>;
GRANT CONNECT, RESOURCE, UNLIMITED TABLESPACE TO EC;
```

> Configure `tnsnames.ora` with Wallet credentials.

#### Option B – Oracle XE

```bash
sqlplus sys/<SYS_PASSWORD>@localhost:1521/XEPDB1 AS SYSDBA @backend/src/main/resources/sql/02-create-user-and-schema.sql
```

---

### 3. Load Sample Data

```bash
sqlplus ec/ec@localhost:1521/XEPDB1 @backend/src/main/resources/sql/03-insert-sample-data.sql
```

---

### 4. Backend Configuration

Edit `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=ec
spring.datasource.password=ec
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
```

Run backend:

```bash
cd backend
mvn clean spring-boot:run
```

API Docs: [http://localhost:8080/docs](http://localhost:8080/docs)

---

### 5. Frontend Configuration

```bash
cd angular-frontend
npm install
npm start
```

Open: [http://localhost:4200](http://localhost:4200)

**OCI Image Integration:**  
Product images are retrieved from an OCI Object Storage bucket.  
For local testing, place fallback images in:  
`angular-frontend/src/assets/images/products`

---

## 📂 Project Structure

```plaintext
backend/
  ├── src/main/java/...          # Spring Boot code
  ├── src/main/resources/sql/    # Schema & data scripts
  └── application.properties     # DB and API config
angular-frontend/
  ├── src/app/                    # Angular components & services
  ├── src/assets/                 # Images, styles
  ├── custom-theme.scss           # Angular Material theme
  └── main.ts                     # App bootstrap
```

---

## 🔮 Next Steps

- Implement PL/SQL procedures (e.g., order handling)
- Add triggers, auditing, and materialized views
- Integrate Spring Boot with PL/SQL
- Add CRUD operations with authentication and tests
