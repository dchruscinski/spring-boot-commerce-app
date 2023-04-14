DROP TABLE IF EXISTS product_availability_event;
CREATE TABLE product_availability_event (
       id serial NOT NULL,
        name VARCHAR(255),
        product_id INTEGER,
        update_moment TIMESTAMP(6) NOT NULL,
        PRIMARY KEY (id)
    );