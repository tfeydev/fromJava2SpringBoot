# Fullstack E-Commerce Application with Spring Boot, Angular and Oracle

This repository contains a customized version of Chad Darby’s e-commerce application, featuring a Spring Boot backend, Angular 20+ frontend, and Oracle XE database.

## 📚 Course Foundation

- **Instructor:** Chad Darby
- **Course:** Full Stack: Angular and Spring Boot (Udemy)
- **Original Database:** MySQL
- **Adaptation:** Oracle XE (including planned PL/SQL enhancements)

## 🏗️ Architecture Overview

- **Frontend:** Angular 20+ (Standalone components, Angular Material)
- **Backend:** Spring Boot 3.5.4 (REST APIs, Spring Data JPA, Validation)
- **Database:** Oracle XE (Schemas: `EC` for e-commerce, `HR` for demo)
- **Data Access:** Hibernate ORM with Oracle dialect

## 🔄 Migration Status

| Component          | Status         | Notes                                    |
| ------------------ | -------------- | ---------------------------------------- |
| EC Schema & Grants | ✅ Completed    | Created `EC` user and granted privileges |
| Table Definitions  | ✅ Completed    | All tables converted from MySQL          |
| Sample Data        | 🔶 In Progress | Product inserts underway                 |
| PL/SQL Logic       | 🔜 Planned     | Stored procedures, triggers, views       |
| Spring Integration | 🔜 Planned     | PL/SQL calls, advanced transactions      |

## 🛠️ Setup Instructions

### 1. Oracle XE

Install Oracle XE 21c (or higher) and ensure the `XEPDB1` Pluggable DB is accessible.

### 2. Initialize `EC` Schema

```bash
sqlplus sys/<SYS_PASSWORD>@localhost:1521/XEPDB1 AS SYSDBA @oracle-ecommerce-ec-setup.sql
```

### 3. Load Sample Data

```bash
sqlplus ec/ec@localhost:1521/XEPDB1 @oracle-ecommerce-sample-data.sql
```

### 4. Backend Configuration

- Update `application.properties` for your Oracle credentials.
- Run:
  ```bash
  mvn spring-boot:run
  ```
- Swagger UI: `http://localhost:8080/docs`

### 5. Angular Frontend

```bash
cd angular-frontend
npm install
npm start
```

- Web app: `http://localhost:4200`
- Copy all image assets into `src/assets` (see `angular.json`).

## 🔮 Next Steps

- Implement PL/SQL procedures for order processing
- Add audit triggers and materialized views
- Integrate advanced transaction management in Spring
