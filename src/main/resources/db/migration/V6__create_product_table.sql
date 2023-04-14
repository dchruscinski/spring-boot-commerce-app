DROP TABLE IF EXISTS product;
CREATE TABLE product (
       id serial NOT NULL,
        color VARCHAR(255),
        is_available boolean NOT NULL,
        name VARCHAR(255),
        price INTEGER NOT NULL,
        product_category_id INTEGER,
        PRIMARY KEY (id)
    );