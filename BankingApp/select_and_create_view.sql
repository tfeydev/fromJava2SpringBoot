SELECT * FROM customers;
SELECT * FROM accounts;


-- SELECT-Abfragen
-- Alle Kunden abrufen
SELECT id, name, email 
FROM customers 
ORDER BY id;

-- Alle Konten abrufen
SELECT id, customer_id, account_number, balance 
FROM accounts 
ORDER BY id; 

-- Kunde mit zugehörigem Konto (Join)
SELECT c.id AS customer_id, c.name, c.email, a.account_number, a.balance 
FROM customers c 
LEFT JOIN accounts a ON c.id = a.customer_id 
ORDER BY c.id;

-- VIEW: Kombinierte Ansicht von Kunden und Konten
CREATE VIEW customer_account_view AS
SELECT c.id AS customer_id, c.name, c.email, a.account_number, a.balance 
FROM customers c 
LEFT JOIN accounts a ON c.id = a.customer_id;

-- Abruf der VIEW
SELECT * FROM customer_account_view ORDER BY customer_id;
