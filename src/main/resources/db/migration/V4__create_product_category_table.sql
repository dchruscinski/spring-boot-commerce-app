DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category (
       id serial NOT NULL,
        name VARCHAR(255),
        PRIMARY KEY (id)
    );