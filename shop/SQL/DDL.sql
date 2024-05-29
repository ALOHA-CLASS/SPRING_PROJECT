CREATE TABLE `USER` (
	`ID`	CHAR(36)	NOT NULL,
	`USERNAME`	VARCHAR(100)	NOT NULL,
	`PASSWORD`	VARCHAR(100)	NOT NULL,
	`NAME`	VARCHAR(100)	NOT NULL,
	`PHONE`	VARCHAR(20)	NOT NULL,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`ENABLED`	INT	NOT NULL	DEFAULT 1
);

CREATE TABLE `USER_AUTH` (
	`ID`	CHAR(36)	NOT NULL,
	`USERANME`	VARCHAR(100)	NOT NULL,
	`AUTH`	VARCHAR(100)	NOT NULL
);

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


DROP TABLE IF EXISTS CATEGORIES;
CREATE TABLE `CATEGORIES` (
	`ID`	CHAR(36)	NOT NULL,
	`CODE`	VARCHAR(100)	NOT NULL UNIQUE,
	`NAME`	VARCHAR(100)	NOT NULL,
	`SEQ`	INT	NOT NULL DEFAULT 1,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE `CARTS` (
	`ID`	CHAR(36)	NOT NULL,
	`PRODUCTS_ID`	CHAR(36)	NOT NULL,
	`USER_ID`	CHAR(36)	NOT NULL,
	`AMOUNT`	INT	NOT NULL	DEFAULT 1,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `ORDERS` (
	`ID`	CHAR(36)	NOT NULL,
	`USER_ID`	CHAR(36)	NOT NULL,
	`TITLE`	VARCHAR(255)	NULL,
	`TOTAL_QUANTITY`	INT	NOT NULL	DEFAULT 1,
	`TOTAL_PRICE`	INT	NOT NULL	DEFAULT 0,
	`STATUS`	ENUM('pending','paid','shipping','delivered','cancelled'	NULL	COMMENT '결제대기, 결제완료, 배송중,배송완료,주문취소',
	`ORDERED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `ORDER_ITEMS` (
	`ID`	CHAR(36)	NOT NULL,
	`ORDERS_ID`	CHAR(36)	NOT NULL,
	`PRODUCTS_ID`	CHAR(36)	NOT NULL,
	`QUANTITY`	INT	NOT NULL	DEFAULT 1,
	`PRICE`	INT	NOT NULL	DEFAULT 0,
	`AMOUNT`	INT	NULL,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `PAYMENTS` (
	`ID`	CHAR(36)	NOT NULL,
	`ORDERS_ID`	CHAR(36)	NOT NULL,
	`PAYMENT_METHOD`	VARCHAR(255)	NULL,
	`STATUS`	VARCHAR(255)	NULL,
	`PAID_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `CANCELLATIONS` (
	`ID`	CHAR(36)	NOT NULL,
	`ORDERS_ID`	CHAR(36)	NOT NULL,
	`TYPE`	ENUM('cancel','return','exchange')	NOT NULL	DEFAULT 'cancel'	COMMENT '주문취소,반품,교환',
	`STATUS`	ENUM('pending','complete'))	NULL	DEFAULT 'pending'	COMMENT '취소요청(대기), 처리완료',
	`REASON`	TEXT	NULL,
	`REFUNDED_AMOUNT`	INT	NOT NULL	DEFAULT 0,
	`IS_CONFIRMED`	TINYINT(1)	NOT NULL	DEFAULT 0	COMMENT '0: 미승인, 1: 승인',
	`IS_REFUND`	TINYINT(1)	NOT NULL	DEFAULT 0	COMMENT '0: 대기, 1: 처리완료',
	`ACCOUNT_NUMBER`	VARCHAR(100)	NULL,
	`BANK_NAME`	VARCHAR(100)	NULL,
	`DEPOSITOR`	VARCHAR(100)	NULL,
	`CANCELED_AT`	TIMESTAMP	NULL,
	`COMPLETED_AT`	TIMESTAMP	NULL,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS FILES;
CREATE TABLE `FILES` (
	`ID`	CHAR(36)	NOT NULL,
	`PARENT_TABLE`	VARCHAR(100)	NOT NULL,
	`PARENT_ID`	VARCHAR(100)	NOT NULL,
	`NAME`	TEXT	NOT NULL,
	`ORIGIN_NAME`	TEXT	NOT NULL,
	`PATH`	TEXT	NOT NULL,
	`SIZE`	BIGINT	NOT NULL	DEFAULT 0,
	`IS_MAIN`	BOOLEAN	NOT NULL	DEFAULT FALSE,
	`SEQ`	INT	NOT NULL	DEFAULT 0,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `USER` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`ID`
);

ALTER TABLE `USER_AUTH` ADD CONSTRAINT `PK_USER_AUTH` PRIMARY KEY (
	`ID`
);

ALTER TABLE `PRODUCTS` ADD CONSTRAINT `PK_PRODUCTS` PRIMARY KEY (
	`ID`
);

ALTER TABLE `BASIC` ADD CONSTRAINT `PK_BASIC` PRIMARY KEY (
	`ID`
);

ALTER TABLE `CARTS` ADD CONSTRAINT `PK_CARTS` PRIMARY KEY (
	`ID`
);

ALTER TABLE `ORDERS` ADD CONSTRAINT `PK_ORDERS` PRIMARY KEY (
	`ID`
);

ALTER TABLE `ORDER_ITEMS` ADD CONSTRAINT `PK_ORDER_ITEMS` PRIMARY KEY (
	`ID`
);

ALTER TABLE `PAYMENTS` ADD CONSTRAINT `PK_PAYMENTS` PRIMARY KEY (
	`ID`
);

ALTER TABLE `CANCELLATIONS` ADD CONSTRAINT `PK_CANCELLATIONS` PRIMARY KEY (
	`ID`
);

