-- Tabelle für Kunden
CREATE TABLE customers (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Tabelle für Konten
CREATE TABLE accounts (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    account_number VARCHAR(20) NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0.00,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Tabelle für Transaktionen
CREATE TABLE transactions (
    id BIGINT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    type VARCHAR(50) NOT NULL,
    FOREIGN KEY (account_id) REFERENCES accounts(id)
);