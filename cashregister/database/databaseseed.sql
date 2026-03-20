CREATE DATABASE IF NOT EXISTS cashregister;
USE cashregister;

CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL
);

DROP TABLE IF EXISTS transaction_items;
DROP TABLE IF EXISTS transactions;

-- one row for the transaction itself
CREATE TABLE IF NOT EXISTS transactions(
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    total DECIMAL(10, 2) NOT NULL,
    payment_method ENUM('cash', 'card') NOT NULL DEFAULT 'cash',
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

-- one row per item in transaction
CREATE TABLE IF NOT EXISTS transaction_items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    
    FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
)

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