CREATE DATABASE IF NOT EXISTS cashregister;
USE cashregister;

CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT, 
    quantity INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT IGNORE INTO products (product_name, unit_price) VALUES
('Soda', 29.50),
('Chips', 34.25),
('Burger', 50.00),
('Coffee', 99.75),
('Donut', 30.25),
('Water', 15.00),
('Sandwich', 75.00),
('Hotdog', 50.00),
('Fries', 45.00),
('Yumburger', 60.00);

ALTER TABLE transactions
ADD COLUMN IF NOT EXISTS payment_method ENUM('cash', 'card') NOT NULL DEFAULT 'cash';