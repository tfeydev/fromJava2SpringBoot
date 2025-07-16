-- Add ingredients
INSERT INTO ingredient (id, name, type) VALUES
  ('FLTO', 'Flour Tortilla', 'WRAP'),
  ('COTO', 'Corn Tortilla', 'WRAP'),
  ('GRBF', 'Ground Beef', 'PROTEIN'),
  ('CARN', 'Carnitas', 'PROTEIN'),
  ('TMTO', 'Diced Tomatoes', 'VEGGIES'),
  ('LETC', 'Lettuce', 'VEGGIES'),
  ('CHED', 'Cheddar', 'CHEESE'),
  ('JACK', 'Monterrey Jack', 'CHEESE'),
  ('SLSA', 'Salsa', 'SAUCE'),
  ('SRCR', 'Sour Cream', 'SAUCE');

-- Optional: Example taco and assignment

INSERT INTO taco (id, name) VALUES (1, 'Carnitas Supreme');
INSERT INTO taco_ingredients (taco_id, ingredient_id) VALUES
  (1, 'CARN'),
  (1, 'CHED'),
  (1, 'SRCR');

-- Optional: Example Order

INSERT INTO taco_order (
  id, delivery_name, delivery_street, delivery_city,
  delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv
) VALUES (
  1, 'Thorsten', 'Rua Teste 123', 'Natal',
  'RN', '80000-000', '4111111111111111', '12/30', '123'
);

-- Taco with order connected
UPDATE taco SET order_id = 1 WHERE id = 1;
