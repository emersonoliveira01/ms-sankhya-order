-- Inserir dados na tabela customers
INSERT INTO customers (id, name, email) VALUES
(1, 'Cliente A', 'cliente.a@example.com'),
(2, 'Cliente B', 'cliente.b@example.com'),
(3, 'Cliente C', 'cliente.c@example.com');

-- Inserir dados na tabela products
INSERT INTO products (id, name, price, stock, description, ncm, cst, unit_of_measure, cfop) VALUES
(101, 'Produto A', 19.99, 100, 'Descrição do Produto A', '1234.56.78', '000', 'UN', '5101'),
(102, 'Produto B', 29.99, 50, 'Descrição do Produto B', '8765.43.21', '000', 'UN', '5101'),
(103, 'Produto C', 39.99, 25, 'Descrição do Produto C', '5432.10.98', '000', 'UN', '5101');

-- Inserir dados na tabela orders
INSERT INTO orders (id, customer_id, sale_date, status) VALUES
(1, 1, '2023-10-05 14:30:00', 'PENDENTE DE SEPARAÇÃO'),
(2, 2, '2023-10-06 10:15:00', 'PENDENTE DE SEPARAÇÃO'),
(3, 3, '2023-10-07 09:00:00', 'PENDENTE DE SEPARAÇÃO');

-- Inserir dados na tabela order_products
INSERT INTO order_products (id, order_id, product_id, quantity) VALUES
(1, 1, 101, 2),
(2, 1, 102, 1),
(3, 2, 103, 3);