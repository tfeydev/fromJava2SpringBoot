
# Fullstack E-Commerce Application with Spring Boot, Angular and Oracle

This project is a customized version of the e-commerce application taught by Chad Darby.  
The backend has been adapted to use **Oracle Database XE** instead of MySQL.

## 📚 Based on the Course
- **Instructor:** Chad Darby
- **Course:** Full Stack: Angular and Spring Boot (Udemy)
- **Original DB:** MySQL
- **Adaptation:** Oracle XE, PL/SQL extensions planned

## 🧱 Architecture Overview

- **Frontend:** Angular (SPA, responsive UI)
- **Backend:** Spring Boot (REST APIs, JPA, Validation, Security)
- **Database:** Oracle XE (Schema `EC`, PL/SQL, Trigger, Views planned)
- **Data Access:** Spring Data JPA (Hibernate with Oracle Dialect)

## 🔁 Status of Migration

| Component        | Status           | Notes                            |
|------------------|------------------|----------------------------------|
| User Schema      | ✅ Done           | `EC` user with required grants   |
| Tables           | ✅ Done           | All MySQL tables converted       |
| Sample Data      | 🟡 In Progress    | Based on original insert scripts |
| PL/SQL Logic     | 🔜 Planned        | Stored procs, triggers, views    |
| Spring Integration | 🔜 Planned      | JDBC/Procedure, Transactions     |

## 🛠 Setup Instructions

### 1. Oracle XE

Install Oracle XE 21c or higher locally. Ensure access to `XEPDB1` PDB.

### 2. Run SQL Scripts

- Create schema and tables:
  ```bash
  sqlplus sys/<password>@localhost:1521/XEPDB1 AS SYSDBA @oracle-ecommerce-ec-setup.sql
  ```

- Insert sample data:
  ```bash
  sqlplus ec/ec@localhost:1521/XEPDB1 @oracle-ecommerce-sample-data.sql
  ```

### 3. Frontend Assets

- Copy the image folder from the course material into:
  ```
  angular-ecommerce/src/assets/images/products
  ```

- Angular will serve images via relative paths.

## 🔮 Next Steps

- Create Oracle PL/SQL procedures (e.g. order processing)
- Add triggers for audit/logging
- Build materialized views for sales analytics
- Integrate stored procedures into Spring Boot
- Use `@Transactional`, test isolation levels

## 📌 Goals

This project aims to provide:

- Full Oracle-based backend experience
- Exposure to advanced DB concepts (PL/SQL, triggers, locks)
- Spring Boot integration with real-world DB behavior
- Fullstack setup ready for deployment and testing

---

This version is ideal for developers who want to go **beyond simple CRUD APIs** and understand how **enterprise applications** integrate deeply with Oracle.

