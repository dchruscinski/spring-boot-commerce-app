DROP TABLE IF EXISTS product_purchase;
CREATE TABLE product_purchase (
       id serial NOT NULL,
        amount INTEGER NOT NULL,
        purchase_date TIMESTAMP(6) NOT NULL,
        customer_id INTEGER,
        product_id INTEGER,
        PRIMARY KEY (id)
    );