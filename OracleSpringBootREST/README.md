# Fullstack E-Commerce Application with Spring Boot, Angular, and Oracle XE

This repository delivers a full-stack e-commerce reference implementation, originally authored by Chad Darby, and now adapted to use **Oracle Database XE** on the backend and **Angular 20+** on the frontend.

---

## 📚 Course Foundation

- **Instructor:** Chad Darby
- **Course:** Full Stack: Angular and Spring Boot (Udemy)
- **Original Database:** MySQL
- **Adaptation:** Oracle XE with planned PL/SQL extensions

---

## 🏗 Architecture

| Layer       | Technology                                              |
| ----------- | ------------------------------------------------------- |
| Frontend    | Angular 20+ (Standalone Components, Material)           |
| Backend     | Spring Boot 3.5.4 (REST APIs, Spring Data JPA)          |
| Database    | Oracle XE (Schemas: `EC` for e-commerce, `HR` for demo) |
| Data Access | Hibernate ORM with Oracle dialect                       |

---

## 🔄 Migration Status

| Component          | Status         | Notes                                      |
| ------------------ | -------------- | ------------------------------------------ |
| EC Schema & Grants | ✅ Completed    | `EC` user created with required privileges |
| Table Definitions  | ✅ Completed    | All tables converted from MySQL scripts    |
| Sample Data        | 🔶 In Progress | Product and category inserts underway      |
| PL/SQL Logic       | 🔜 Planned     | Stored procedures, triggers, and views     |
| Spring Integration | 🔜 Planned     | PL/SQL calls, transaction tuning           |

---

## 🚀 Getting Started

### 1. Prerequisites

- **Oracle XE 21c** or higher installed locally
- **Java 21** and **Maven 3.8+**
- **Node.js** and **npm** for frontend

### 2. Initialize `EC` Schema

```bash
sqlplus sys/<SYS_PASSWORD>@localhost:1521/XEPDB1 AS SYSDBA @backend/src/main/resources/sql/02-create-user-and-schema.sql
```

### 3. Load Sample Data

```bash
sqlplus ec/ec@localhost:1521/XEPDB1 @backend/src/main/resources/sql/03-insert-sample-data.sql
```

### 4. Run Spring Boot Backend

1. Configure your Oracle credentials in `backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
   spring.datasource.username=ec
   spring.datasource.password=ec
   spring.jpa.show-sql=true
   spring.jpa.hibernate.ddl-auto=none
   spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
   ```
2. Start the application:
   ```bash
   cd backend
   mvn clean spring-boot:run
   ```
3. Access OpenAPI docs at [http://localhost:8080/docs](http://localhost:8080/docs)

### 5. Run Angular Frontend

1. Copy product images into `angular-frontend/src/assets/images/products`
2. Install dependencies and start dev server:
   ```bash
   cd angular-frontend
   npm install
   npm start
   ```
3. Open the app at [http://localhost:4200](http://localhost:4200)

---

## 📁 Project Structure

```
├── backend/
│   ├── src/main/java/...           # Spring Boot application
│   ├── src/main/resources/sql/     # SQL setup scripts
│   └── src/main/resources/         # application.properties, OpenAPI config

└── angular-frontend/
    ├── src/app/
    │   ├── core/navigation/        # Standalone nav component
    │   ├── pages/                  # Standalone page components (Home, Products, Employees)
    │   └── service/                # HttpClient services
    ├── src/assets/                 # Logo and product images
    ├── src/custom-theme.scss       # Angular Material theme overrides
    ├── src/styles.css              # Global styles
    └── src/main.ts                 # bootstrapApplication
```

---

## 🔮 Next Steps

- Develop PL/SQL procedures for order processing
- Add audit triggers, materialized views for analytics
- Integrate advanced transaction management and retry logic
- Expand Spring controllers with full CRUD, security, and tests

---

*This project follows best practices: server-side pagination, DTO mapping, standalone Angular components, Angular Material, Lombok for entities, and OpenAPI documentation.*

