
# Oracle E-Commerce Database Setup

This project sets up an Oracle XE schema for a full-stack e-commerce application.

## 🏗️ 1. Requirements

- Oracle Database XE installed and running
- SQL Developer or SQL*Plus access
- Oracle PDB: `XEPDB1` (default)
- Admin user: `SYS` or similar with `SYSDBA`

## 👤 2. Create Schema/User

Login as SYSDBA and run the script to create the `EC` user and database structure:

```bash
sqlplus sys/<your_sys_password>@localhost:1521/XEPDB1 AS SYSDBA @oracle-ecommerce-ec-setup.sql
```

This will:
- Create user `ec` with password `ec`
- Grant required privileges
- Create all required tables

## 🧾 3. Load Sample Data

After logging in as the new user `ec`, load the sample data:

```bash
sqlplus ec/ec@localhost:1521/XEPDB1 @oracle-ecommerce-sample-data.sql
```

This script will insert:
- Product categories
- Products (books, mugs, etc.)

## 📁 4. Image Assets

The database only stores relative image paths, like:

```
assets/images/products/books/book-luv2code-1000.png
```

To match these, copy your product images into your **Angular or React frontend** under:

```
src/assets/images/products/
```

Make sure `angular.json` or the React asset config includes `src/assets`.

## ✅ Ready

Now your Oracle database is initialized with test data and image references.

You can build the frontend (Angular or React) and connect to this backend via a Spring Boot REST API.
