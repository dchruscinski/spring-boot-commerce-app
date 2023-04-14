DROP TABLE IF EXISTS product_manager;
CREATE TABLE product_manager (
       id serial NOT NULL,
        email VARCHAR(255),
        last_name VARCHAR(255),
        name VARCHAR(255),
        category_id INTEGER,
        PRIMARY KEY (id)
    );