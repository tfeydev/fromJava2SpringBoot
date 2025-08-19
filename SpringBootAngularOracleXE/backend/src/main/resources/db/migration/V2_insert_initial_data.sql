-- Insert Product Categories
INSERT INTO product_category (category_name) VALUES ('Books');
INSERT INTO product_category (category_name) VALUES ('Coffee Mugs');
INSERT INTO product_category (category_name) VALUES ('Mouse Pads');
INSERT INTO product_category (category_name) VALUES ('Luggage Tags');

-- Insert Sample Products (Books)
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('BOOK-TECH-1000', 'Crash Course in Python', 'Learn Python at your own pace.', 'assets/images/products/books/book-luv2code-1000.png', 1, 100, 14.99, 1, SYSTIMESTAMP);

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('BOOK-TECH-1001', 'Become a Guru in JavaScript', 'Learn JavaScript in a practical way.', 'assets/images/products/books/book-luv2code-1001.png', 1, 100, 20.99, 1, SYSTIMESTAMP);

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('BOOK-TECH-1002', 'Exploring Vue.js', 'Master Vue.js step-by-step.', 'assets/images/products/books/book-luv2code-1002.png', 1, 100, 14.99, 1, SYSTIMESTAMP);

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('BOOK-TECH-1003', 'Advanced Techniques in Big Data', 'Take your Big Data skills to the next level.', 'assets/images/products/books/book-luv2code-1003.png', 1, 100, 13.99, 1, SYSTIMESTAMP);

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('BOOK-TECH-1004', 'Crash Course in Big Data', 'Learn Big Data fast.', 'assets/images/products/books/book-luv2code-1004.png', 1, 100, 18.99, 1, SYSTIMESTAMP);

-- Insert Sample Products (Coffee Mugs)
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('COFFEEMUG-1000', 'Coffee Mug - Express', 'Elegant coffee mug with fractal design.', 'assets/images/products/coffeemugs/coffeemug-luv2code-1000.png', 1, 100, 18.99, 2, SYSTIMESTAMP);

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('COFFEEMUG-1001', 'Coffee Mug - Cherokee', 'Unique and stylish mug.', 'assets/images/products/coffeemugs/coffeemug-luv2code-1001.png', 1, 100, 18.99, 2, SYSTIMESTAMP);

INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, date_created)
VALUES ('COFFEEMUG-1002', 'Coffee Mug - Sweeper', 'Fractal artwork design.', 'assets/images/products/coffeemugs/coffeemug-luv2code-1002.png', 1, 100, 18.99, 2, SYSTIMESTAMP);
