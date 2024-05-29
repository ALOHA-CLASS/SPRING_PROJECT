DROP TABLE IF EXISTS products;
CREATE TABLE `PRODUCTS` (
	`ID`	CHAR(36)	NOT NULL PRIMARY KEY,
	`NAME`	VARCHAR(100)	NOT NULL,
	`CATEGORY`	VARCHAR(100)	NULL,
	`DESCRIPTION`	VARCHAR(200)	NULL,
	`CONTENT`	TEXT	NULL,
	`PRICE`	INT	NOT NULL,
	`STOCK`	INT	NULL	DEFAULT 0,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);


TRUNCATE PRODUCTS;


INSERT INTO `PRODUCTS` (`ID`, `NAME`, `CONTENT`, `PRICE`, `STOCK`, `CREATED_AT`, `UPDATED_AT`) VALUES
(UUID(), 'Product 1', 'Description for product 1', 100, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), 'Product 2', 'Description for product 2', 200, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), 'Product 3', 'Description for product 3', 300, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), 'Product 4', 'Description for product 4', 400, 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), 'Product 5', 'Description for product 5', 500, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


SELECT * FROM products;


SELECT p.id
              ,p.name
              ,p.category
              ,( SELECT name FROM categories WHERE code = p.category ) category_name
              ,p.content
              ,p.price
              ,p.stock
              ,p.created_at
              ,p.updated_at
        FROM products p


		;

		SELECT * FROM categories;


SELECT p.id
              ,p.name
              ,p.category
              ,p.description
              ,p.content
              ,p.price
              ,p.stock
              ,p.created_at
              ,p.updated_at
              ,( SELECT id FROM files WHERE parent_table = 'products' AND parent_id = p.id AND is_main = 1 ) thumbnail_id 
              ,( SELECT name FROM categories WHERE code = p.category ) category_name
        FROM products p
		;