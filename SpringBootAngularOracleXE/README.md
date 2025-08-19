# Spring Boot (Java 21) + Angular (20+) + Oracle XE (Docker) — Fullstack Setup Guide

## Overview
This repository contains a full-stack e‑commerce example:
- **Backend**: Spring Boot (Java 21, Maven)
- **Frontend**: Angular 20+ (Angular CLI)
- **Database**: Customized Oracle XE Docker image (gvenzl/oracle-xe variant)

This document consolidates setup steps, scripts, SQL initialization, Docker usage, development workflow, and troubleshooting. Do NOT commit secrets (.env files) to version control.

**Note on Origin**  
This project is based on the Udemy course by Chad Darby, which originally used MySQL as the database.  
All examples, schema, and sample data were adapted from that course. In this repository, the backend now uses a **customized Oracle XE Docker image** (instead of MySQL), and the application has been adjusted accordingly. The functionality, endpoints, and example data follow the original course structure but are fully compatible with Oracle XE.


---

## Quickstart (short)
1. Copy example env and edit:
```bash
cp .env-dev.example .env-dev
# Edit .env-dev with real values (do NOT commit .env-dev)
```
2. Start Oracle XE (Docker):
```bash
docker run -d -p 1521:1521 --name oracle-xe -e ORACLE_PASSWORD=oracle -v oracle-data:/opt/oracle/oradata gvenzl/oracle-xe
```
3. Start services:
```bash
chmod +x start-dev.sh
./start-dev.sh
```
4. Access frontend: http://localhost:4200, backend API: http://localhost:8080

---

## Prerequisites
- Java 21 (LTS or newer)
- Maven 3.8+ or the Maven wrapper (`./mvnw`)
- Node.js 18+ and npm
- Angular CLI compatible with Angular 20+ (local or via npx)
- Docker Desktop (WSL2 integration on Windows)
- Git
- Optional: SQL*Plus / Oracle Instant Client for manual DB access

---

## Files and locations (project layout)
```
/ (repo root)
  .env-dev.example      # example env for dev (DO NOT commit secrets)
  start-dev.sh          # helper script to start backend+frontend (executable)
  docker-compose.yml    # optional (recommendation)
  backend/              # Spring Boot (Maven) project
    pom.xml
    src/main/resources/application-dev.properties
  frontend/             # Angular project (angular.json, package.json)
  db/scripts/           # SQL scripts for schema + sample data
    01-dockerOracleXEUserSchema.sql
    02-dockerOracleXETables.sql
    03-dockerOracleXEData.sql
```

---

## .env-dev (example)
Create `.env-dev` from the example and fill in real credentials. Example keys used by this guide:

```env
# .env-dev (do not commit)
DB_URL=jdbc:oracle:thin:@localhost:1521/XEPDB1
DB_USERNAME=EC
DB_PASSWORD=ec

FRONTEND_PORT=4200
BACKEND_PORT=8080
```
> Use uppercase with underscores for environment variables. `application-dev.properties` should reference these with `${DB_URL}` etc.

---

## Oracle XE (Docker) — setup and customizing note
This project expects a **customized Oracle XE Docker image** derived from `gvenzl/oracle-xe`. The customization may include preloaded initialization or altered environment defaults. Use the provided image/README if available. If not, use the upstream image and apply SQL scripts after container launch.

### Pull image
```bash
docker pull gvenzl/oracle-xe
# or your-custom-image:tag if you published an adjusted image
```

### Run container
```bash
docker run -d -p 1521:1521 --name oracle-xe   -e ORACLE_PASSWORD=oracle   -v oracle-data:/opt/oracle/oradata   gvenzl/oracle-xe
```

### Verify container
```bash
docker ps
docker logs oracle-xe --tail 200
```

---

## Initialize EC schema and load sample data (inside container)
1. Copy SQL scripts into the container and run them as SYS and then EC as required:

```bash
docker cp db/scripts/01-dockerOracleXEUserSchema.sql oracle-xe:/tmp/01-dockerOracleXEUserSchema.sql
docker cp db/scripts/02-dockerOracleXETables.sql oracle-xe:/tmp/02-dockerOracleXETables.sql
docker cp db/scripts/03-dockerOracleXEData.sql oracle-xe:/tmp/03-dockerOracleXEData.sql

docker exec -it oracle-xe sqlplus SYSTEM/oracle@XE @/tmp/01-dockerOracleXEUserSchema.sql
docker exec -it oracle-xe sqlplus EC/ec@XE @/tmp/02-dockerOracleXETables.sql
docker exec -it oracle-xe sqlplus EC/ec@XE @/tmp/03-dockerOracleXEData.sql
```

2. Verify data (example queries):
```bash
docker exec -it oracle-xe sqlplus EC/ec@XE
# then inside SQL*Plus:
SELECT COUNT(*) FROM EC.PRODUCT_CATEGORY;
SELECT COUNT(*) FROM EC.PRODUCT;
SELECT * FROM EC.PRODUCT WHERE CATEGORY_ID = 1;
```

Expected: 4 categories, ~100 products (as provided by sample data).

---

## SQL scripts (location & purpose)
Place these files in `db/scripts/`:
- `01-dockerOracleXEUserSchema.sql` — creates `EC` user and grants.
- `02-dockerOracleXETables.sql` — DDL for tables: PRODUCT_CATEGORY, PRODUCT, CUSTOMER, ADDRESS, ORDERS, ORDER_ITEM, COUNTRY, STATE.
- `03-dockerOracleXEData.sql` — INSERT statements for categories, products and other sample rows with timestamps for DATE_CREATED/LAST_UPDATED.

(Use the exact DDL and sample data already present in your repo; do not modify naming unless you update application mappings.)

---

## Application configuration — Spring Boot (backend)
Use profile-based config. Example `backend/src/main/resources/application-dev.properties`:

```properties
# application-dev.properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect

spring.data.rest.base-path=/api
springdoc.swagger-ui.path=/docs
```
**Important:** Do not use `${spring.datasource.url}` placeholders that reference themselves. Use generic env names (DB_URL, DB_USERNAME, DB_PASSWORD) and reference them in properties as shown.

---

## Development start script (`start-dev.sh`)
Place `start-dev.sh` in repo root and make executable (`chmod +x start-dev.sh`). Script behavior:
- resolves repo root reliably
- sources `.env-dev` and exports variables
- starts backend via `mvn -f backend/pom.xml spring-boot:run`
- starts frontend via `npx ng serve --no-open --port $FRONTEND_PORT` inside `frontend`
- traps exit and kills background processes to avoid orphaned processes

Example `start-dev.sh` (already provided in repo):
```bash
#!/usr/bin/env bash
# start-dev.sh - start backend and frontend for development
set -euo pipefail
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ENV_FILE="$SCRIPT_DIR/.env-dev"
if [ ! -f "$ENV_FILE" ]; then
  echo ".env-dev not found in $SCRIPT_DIR"
  exit 1
fi
set -a
source "$ENV_FILE"
set +a
BACKEND_DIR="$SCRIPT_DIR/backend"
FRONTEND_DIR="$SCRIPT_DIR/frontend"
mvn -f "$BACKEND_DIR/pom.xml" spring-boot:run &
BACKEND_PID=$!
(
  cd "$FRONTEND_DIR" || exit 1
  npx --yes ng serve --no-open --port "${FRONTEND_PORT:-4200}"
) &
FRONTEND_PID=$!
cleanup() {
  kill "$BACKEND_PID" "$FRONTEND_PID" 2>/dev/null || true
  wait "$BACKEND_PID" 2>/dev/null || true
  wait "$FRONTEND_PID" 2>/dev/null || true
}
trap cleanup EXIT
wait
```

---

## Frontend (Angular) notes
- Ensure `frontend/package.json` contains a start script or use `ng serve` via npx.
- Static assets (images) expected under `frontend/src/assets/images/products/` with relative paths stored in DB.
- Typical commands:
```bash
cd frontend
npm install
npx ng serve --no-open --port 4200
```

---

## VS Code Oracle tooling (optional)
Install "Oracle Developer Tools for VS Code" and configure a connection:
- Username: `EC`
- Password: `ec`
- Host: `localhost`
- Port: `1521`
- SID / Service: `XE` or `XEPDB1` depending on your container image

You can run scripts directly from VS Code using the extension.

---

## Docker Compose example (recommended for third parties)
Provide `docker-compose.yml` and `.env` usage so third parties can `git clone` and run without exposing secrets in repo. Example snippet:

```yaml
version: '3.8'
services:
  oracle-xe:
    image: gvenzl/oracle-xe
    environment:
      ORACLE_PASSWORD: oracle
    ports:
      - "1521:1521"
    volumes:
      - oracle-data:/opt/oracle/oradata

  app:
    build: ./backend
    env_file:
      - .env-dev
    depends_on:
      - oracle-xe
    ports:
      - "8080:8080"

volumes:
  oracle-data:
```
Usage for new contributor:
```bash
cp .env-dev.example .env-dev  # fill in credentials
docker compose up --build
# OR run start-dev.sh after the DB container is running
```

---

## Security & Git best practices
- Add `.env-dev` and any real `.env` to `.gitignore`.
- Commit `.env-dev.example` with placeholders only.
- Use secrets manager or CI/CD secret store for production credentials; do NOT store production secrets in repo.

---

## Troubleshooting
- `Failed to determine suitable jdbc url` → ensure `.env-dev` is present and DB_URL is correct, or export env vars before starting.
- `Driver ... claims to not accept jdbcUrl, ${DB_URL}` → indicates placeholder not resolved; ensure env was exported before Spring Boot starts.
- `ng: command not found` → use `npx ng` or install Angular CLI globally: `npm i -g @angular/cli`.
- Docker container issues → `docker logs oracle-xe` and `docker ps`.
- SQL*Plus issues → verify Oracle Instant Client or use `docker exec -it oracle-xe sqlplus` inside container.

---

## Verification checklist
- Oracle container running and reachable: `docker ps`, `telnet localhost 1521` or `nc -vz localhost 1521`
- EC schema exists and tables are populated.
- Backend starts and exposes REST endpoints: `http://localhost:8080/api/...`
- Frontend loads product data from backend at `http://localhost:4200`

---

## Appendices

### 1. Example SQL: create user (db/scripts/01-dockerOracleXEUserSchema.sql)
```sql
CREATE USER EC IDENTIFIED BY ec
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA UNLIMITED ON users;

GRANT CONNECT, RESOURCE TO EC;
ALTER USER EC ACCOUNT UNLOCK;
COMMIT;
```

### 2. Example DDL: tables (db/scripts/02-dockerOracleXETables.sql)
(See project DDL; ensure constraints and naming match JPA mappings.)

### 3. Sample data load (db/scripts/03-dockerOracleXEData.sql)
(Contains INSERTs for categories, products, timestamps for DATE_CREATED and LAST_UPDATED.)

---

## Contact / Notes
- This README consolidates all previous documentation into a single authoritative guide.
- Keep `.env-dev.example` up to date with required variables.
- For production, use a proper secrets manager, container orchestration and CI secrets injection.

---

## Docker Compose (development)

A `docker-compose-dev.yml` is provided to run the full stack locally using Docker. It uses the `gvenzl/oracle-xe` image for Oracle XE (or a customized image if you published one). The compose file builds the backend and frontend images from the local Dockerfiles and passes environment variables from `.env-dev`.

**Usage**:
```bash
cp .env-dev.example .env-dev   # fill in credentials (do NOT commit .env-dev)
docker compose -f docker-compose-dev.yml up --build
```

**Services**:
- `oracle-xe` — Oracle XE database (ports: 1521).
- `backend` — Spring Boot backend (build from `./backend/Dockerfile`, ports: 8080).
- `frontend` — Angular production build served by nginx (build from `./frontend/Dockerfile`, mapped to host port 4200).

### docker-compose-dev.yml
Copy the file `docker-compose-dev.yml` at repository root with the following content:
```yaml
version: '3.8'
services:
  oracle-xe:
    image: gvenzl/oracle-xe
    container_name: oracle-xe
    environment:
      - ORACLE_PASSWORD=oracle
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

### Notes on Dockerfiles

**Frontend Dockerfile** (multi-stage):
```dockerfile
# Build Stage
FROM node:22 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
ARG NODE_ENV=development
RUN if [ "$NODE_ENV" = "production" ]; then npm run build --configuration production; fi

# Runtime Stage
FROM nginx:alpine
COPY --from=build /app/dist/frontend /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

**Backend Dockerfile** (multi-stage):
```dockerfile
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
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

**Important notes**:
- The frontend Dockerfile expects the Angular `dist` output directory at `/app/dist/frontend`. Adjust the `COPY` source if your Angular output directory name differs (e.g. `dist/<project-name>`).
- For development, you may prefer to run the frontend with `ng serve` locally instead of building an image; the compose setup here serves the built static assets via nginx for simplicity and parity with production builds.
- The `backend` service uses the build arg `SPRING_PROFILES_ACTIVE` and reads database credentials from `.env-dev` through `env_file`. Ensure your `application-dev.properties` references `${DB_URL}`, `${DB_USERNAME}`, `${DB_PASSWORD}` (or use DB_URL naming consistent with your `.env-dev`).
