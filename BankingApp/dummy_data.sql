-- Kunde 1
INSERT INTO customers (id, name, email) VALUES (1, 'John Doe', 'john.doe@example.com');
INSERT INTO accounts (id, customer_id, account_number, balance) VALUES (1, 1, 'ACC001', 1000.50);

-- Kunde 2
INSERT INTO customers (id, name, email) VALUES (2, 'Jane Smith', 'jane.smith@example.com');
INSERT INTO accounts (id, customer_id, account_number, balance) VALUES (2, 2, 'ACC002', 2500.75);

-- Kunde 3
INSERT INTO customers (id, name, email) VALUES (3, 'Carlos Silva', 'carlos.silva@example.com');
INSERT INTO accounts (id, customer_id, account_number, balance) VALUES (3, 3, 'ACC003', 500.25);

-- Kunde 4
INSERT INTO customers (id, name, email) VALUES (4, 'Maria Oliveira', 'maria.oliveira@example.com');
INSERT INTO accounts (id, customer_id, account_number, balance) VALUES (4, 4, 'ACC004', 3500.00);

-- Kunde 5
INSERT INTO customers (id, name, email) VALUES (5, 'Pedro Almeida', 'pedro.almeida@example.com');
INSERT INTO accounts (id, customer_id, account_number, balance) VALUES (5, 5, 'ACC005', 750.90);