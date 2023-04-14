DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
       id serial NOT NULL,
        email VARCHAR(255),
        last_name VARCHAR(255),
        name VARCHAR(255),
        phone_number VARCHAR(255),
        PRIMARY KEY (id)
        );