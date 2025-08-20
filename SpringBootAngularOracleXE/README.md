# Spring Boot (Java 21) + Angular (20+) + Oracle XE (Docker) — Fullstack Setup Guide

## Overview

This repository contains a full-stack e-commerce example:

* **Backend**: Spring Boot (Java 21, Maven)
* **Frontend**: Angular 20+ (Angular CLI)
* **Database**: Oracle XE (Docker, `gvenzl/oracle-xe` variant)

This guide consolidates setup steps, SQL initialization, Docker usage, development workflow, best practices, and troubleshooting. **Do NOT commit secrets** (e.g., `.env-dev`) to version control.

**Origin & Context**
This project is based on the Udemy course by Chad Darby (originally MySQL). Schema, sample data, and endpoints were adapted for Oracle XE. The repository preserves the original course logic while implementing a full Oracle XE setup.

---

## Quickstart

```bash
cp .env-dev.example .env-dev   # edit with real values (do NOT commit)

docker compose -f docker-compose-dev.yml up --build
# or: ./start-dev.sh
```

* Frontend: [http://localhost:4200](http://localhost:4200)
* Backend API: [http://localhost:8080](http://localhost:8080)
* Database: Oracle XE at localhost:1521/XE (or XEPDB1)

---

## Prerequisites

* Java 21 (LTS)
* Maven 3.8+ or `./mvnw`
* Node.js 18+, npm
* Angular CLI 20+ (local or via npx)
* Docker Desktop (WSL2 integration on Windows)
* Git
* Optional: Oracle Instant Client for manual DB access

---

## Project Layout

```
/ (repo root)
  .env-dev.example      # example env (DO NOT commit secrets)
  .gitignore
  start-dev.sh          # helper script (dev)
  docker-compose-dev.yml# Docker Compose setup
  backend/              # Spring Boot (Maven)
    pom.xml
    src/main/resources/application-dev.properties
  frontend/             # Angular project
  db/scripts/           # SQL scripts for schema + sample data
    01-user-schema.sql
    02-tables.sql
    03-data.sql
```

---

## Environment Variables

Create `.env-dev` from the example:

```env
DB_URL=jdbc:oracle:thin:@localhost:1521/XE
DB_USERNAME=EC
DB_PASSWORD=ec

FRONTEND_PORT=4200
BACKEND_PORT=8080
```

* Use uppercase and underscores for variable names.
* Reference these in `application-dev.properties` as `${DB_URL}`, `${DB_USERNAME}`, `${DB_PASSWORD}`.

---

## Oracle XE Setup

### Pull Image

```bash
docker pull gvenzl/oracle-xe
```

### Run Container

```bash
docker run -d -p 1521:1521 --name oracle-xe \
  -e ORACLE_PASSWORD=oracle \
  -v oracle-data:/opt/oracle/oradata \
  gvenzl/oracle-xe
```

### Verify

```bash
docker ps
docker logs oracle-xe --tail 100
```

### Initialize Schema & Data

```bash
docker cp db/scripts/01-user-schema.sql oracle-xe:/tmp/
docker cp db/scripts/02-tables.sql oracle-xe:/tmp/
docker cp db/scripts/03-data.sql oracle-xe:/tmp/

docker exec -it oracle-xe sqlplus SYSTEM/oracle@XE @/tmp/01-user-schema.sql
docker exec -it oracle-xe sqlplus EC/ec@XE     @/tmp/02-tables.sql
docker exec -it oracle-xe sqlplus EC/ec@XE     @/tmp/03-data.sql
```

### Verification

```sql
SELECT COUNT(*) FROM EC.PRODUCT_CATEGORY; -- expect 4
SELECT COUNT(*) FROM EC.PRODUCT;          -- ~100
```

---

## Spring Boot Configuration

`backend/src/main/resources/application-dev.properties`

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.show-sql=true

spring.data.rest.base-path=/api
springdoc.swagger-ui.path=/docs
```

* Ensure environment variables are used instead of hard-coded credentials.

---

## Development Script (`start-dev.sh`)

```bash
#!/usr/bin/env bash
set -euo pipefail
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ENV_FILE="$SCRIPT_DIR/.env-dev"
[ -f "$ENV_FILE" ] || { echo ".env-dev missing"; exit 1; }
set -a; source "$ENV_FILE"; set +a

mvn -f backend/pom.xml spring-boot:run -Dspring-boot.run.profiles=dev &
BACKEND_PID=$!

(cd frontend && npx ng serve --no-open --port "${FRONTEND_PORT:-4200}") &
FRONTEND_PID=$!

trap "kill $BACKEND_PID $FRONTEND_PID 2>/dev/null" EXIT
wait
```

* Resolves repo root dynamically.
* Starts backend & frontend with proper environment variables.
* Cleans up background processes on exit.

---

## Docker Compose (Development)

`docker-compose-dev.yml`

```yaml
version: '3.8'
services:
  oracle-xe:
    image: gvenzl/oracle-xe
    container_name: oracle-xe
    environment:
      ORACLE_PASSWORD: oracle
    ports:
      - "1521:1521"
    volumes:
      - oracle-data:/opt/oracle/oradata
    restart: unless-stopped

  backend:
    build:
      context: ./backend
      dockerfile: Dockerfile
      args:
        SPRING_PROFILES_ACTIVE: dev
    env_file:
      - .env-dev
    depends_on:
      - oracle-xe
    ports:
      - "8080:8080"
    restart: on-failure

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
      args:
        NODE_ENV: development
    env_file:
      - .env-dev
    ports:
      - "4200:80"
    restart: on-failure

volumes:
  oracle-data:
```

---

## Backend Dockerfile

```dockerfile
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src
ARG SPRING_PROFILES_ACTIVE=dev
RUN ./mvnw clean package -DskipTests -P${SPRING_PROFILES_ACTIVE}

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
ENTRYPOINT ["java","-jar","app.jar"]
```

## Frontend Dockerfile

```dockerfile
FROM node:22 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
ARG NODE_ENV=production
RUN npm run build --configuration=${NODE_ENV}

FROM nginx:alpine
COPY --from=build /app/dist/frontend /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

> Adjust `dist/frontend` if your Angular output differs.

---

## Security Best Practices

* Do not commit `.env-dev` with real secrets.
* Commit `.env-dev.example` with placeholders only.
* Use unique `ORACLE_PASSWORD` outside demo environments.
* In production, use secrets management (Vault, AWS Secrets Manager, etc.).

---

## Troubleshooting

* **DB\_URL not resolved** → ensure `.env-dev` is sourced.
* **Service XE vs. XEPDB1 mismatch** → check container logs.
* **Angular build empty** → verify NODE\_ENV.
* **ng command not found** → use `npx ng`.
* **Maven wrapper missing** → ensure `mvnw` + `.mvn` copied in Dockerfile.

---

## Verification Checklist

* Oracle XE container running (`docker ps`, `nc -vz localhost 1521`).
* EC schema exists and tables populated.
* Backend REST endpoints accessible at [http://localhost:8080/api/](http://localhost:8080/api/)...
* Frontend displays product data at [http://localhost:4200](http://localhost:4200).

---

## Appendices

### 1. Example SQL: Create User (`db/scripts/01-user-schema.sql`)

```sql
CREATE USER EC IDENTIFIED BY ec
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA UNLIMITED ON users;

GRANT CONNECT, RESOURCE TO EC;
ALTER USER EC ACCOUNT UNLOCK;
COMMIT;
```

### 2. Example DDL: Tables (`db/scripts/02-tables.sql`)

* PRODUCT\_CATEGORY, PRODUCT, CUSTOMER, ADDRESS, ORDERS, ORDER\_ITEM, COUNTRY, STATE
* Ensure constraints match JPA mappings in Spring Boot project.

### 3. Sample Data Load (`db/scripts/03-data.sql`)

* Insert statements for categories, products, and sample rows.
* Include timestamps for DATE\_CREATED and LAST\_UPDATED.

### 4. VS Code Oracle Tooling (Optional)

* Install Oracle Developer Tools for VS Code.
* Configure connection:

  * Username: `EC`
  * Password: `ec`
  * Host: `localhost`, Port: `1521`
  * Service/SID: `XE` or `XEPDB1`
* Run scripts directly from VS Code.

---

## Notes

* Keep `.env-dev.example` up to date.
* For production, integrate secrets manager, container orchestration, and CI secrets injection.
* This guide merges previous documentation into a single authoritative reference for Oracle XE + Spring Boot + Angular fullstack development.
