# Fullstack E-Commerce Application with Spring Boot, Angular, and Oracle Cloud

This repository is a customized version of Chad Darby’s **Full Stack: Angular and Spring Boot** course project (Sections 1–11 completed), adapted to use **Oracle Autonomous Database (ADB)** and **OCI Object Storage** for product images.  
Section 14 development will start soon.

---

## 📚 Course & Adaptation

| Item         | Details                                                                 |
|--------------|-------------------------------------------------------------------------|
| Instructor   | Chad Darby                                                              |
| Course       | Full Stack: Angular and Spring Boot (Udemy)                             |
| Original DB  | MySQL                                                                   |
| Adaptation   | Oracle Autonomous Database backend + OCI Object Storage for images     |
| Enhancements | Planned PL/SQL logic, triggers, and materialized views                  |

---

## 🏗 Architecture

| Layer      | Technology                                                            |
|------------|------------------------------------------------------------------------|
| Frontend   | Angular 20+ (Standalone Components, Angular Material)                  |
| Backend    | Spring Boot 3.5.4 (REST APIs, Spring Data JPA, OpenAPI/Swagger)         |
| Database   | Oracle Autonomous Database (Schema `EC` for e-commerce)                 |
| Data Layer | Hibernate ORM with Oracle dialect                                      |
| Storage    | OCI Object Storage Bucket for product images                           |

---

## 🔄 Migration Progress

| Component            | Status       | Notes                                                                            |
|----------------------|--------------|----------------------------------------------------------------------------------|
| EC Schema & Grants   | ✅ Completed | User `EC` created with required roles and privileges                             |
| Table Definitions    | ✅ Completed | All MySQL tables migrated to Oracle                                              |
| Sample Data          | ✅ Completed | Product/category data loaded                                                     |
| OCI Object Storage   | ✅ Completed | Bucket configured and integrated for image hosting                               |
| PL/SQL Logic         | 🔜 Planned   | Stored procedures, triggers, and views                                           |
| Spring Integration   | 🔜 Planned   | PL/SQL integration and performance tuning                                        |

---

## 🚀 Getting Started

### 1. Prerequisites

- Oracle Autonomous Database (OCI)
- Java 21, Maven 3.8+
- Node.js & npm for Angular
- OCI account with Object Storage bucket
- Docker & Docker Compose (for VM deployment)

---

### 2. Database Setup (Oracle Autonomous Database)

1. Create an ADB instance (e.g., `ecommerce`) in OCI Console  
2. Download and extract the **Wallet**  
3. Connect as `ADMIN`:

```sql
CREATE USER EC IDENTIFIED BY <password>;
GRANT CONNECT, RESOURCE, UNLIMITED TABLESPACE TO EC;
```

> Configure `tnsnames.ora` with Wallet credentials.

---

### 3. Load Sample Data

```bash
sqlplus ec/<password>@<TNS_ALIAS> @backend/src/main/resources/sql/03-insert-sample-data.sql
```

---

### 4. Backend Configuration

Edit `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@<TNS_ALIAS>
spring.datasource.username=ec
spring.datasource.password=<password>
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

## 🐳 Deployment on Oracle Cloud VM (Ubuntu)

### 1. Install Docker & Docker Compose
```bash
sudo apt update && sudo apt upgrade -y
sudo apt install docker.io docker-compose -y
sudo usermod -aG docker $USER
```
Re-login to apply group changes.  

### 2. Project Setup
Clone the repository:
```bash
git clone https://github.com/your/repo.git
cd repo
```

### 3. Wallet & Environment
Download the **ADB Wallet** from OCI Console and copy to VM:
```bash
mkdir -p ~/dev/wallet
unzip Wallet_<DBNAME>.zip -d ~/dev/wallet
```

Adjust `docker-compose.yml`:
```yaml
services:
  backend:
    build: ./backend
    environment:
      - TNS_ADMIN=/wallet
    volumes:
      - /home/ubuntu/dev/wallet:/wallet
    ports:
      - "8080:8080"

  frontend:
    build:
      context: ./angular-frontend
      args:
        API_BASE_URL: http://backend:8080/api
    ports:
      - "80:80"
    depends_on:
      - backend
```

### 4. Build & Run
```bash
docker-compose up -d --build
```

- Backend API: `http://<VM_PUBLIC_IP>:8080/api/products`  
- Frontend UI: `http://<VM_PUBLIC_IP>`  

> ⚠️ APIs are exposed for **training/demo purposes**.  
> In production, secure via OCI Load Balancer, API Gateway, or private subnets.  

### 5. Stop & Clean Up
```bash
docker-compose down
```

---

## 🔮 Next Steps

- Implement PL/SQL procedures (e.g., order handling)
- Add triggers, auditing, and materialized views
- Integrate Spring Boot with PL/SQL
- Add CRUD operations with authentication and tests
- Secure Docker deployment with OCI networking & load balancing  
