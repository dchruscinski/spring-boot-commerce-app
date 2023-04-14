ALTER TABLE IF EXISTS product
    ADD CONSTRAINT FKcwclrqu392y86y0pmyrsi649r
    FOREIGN KEY (product_category_id)
    REFERENCES product_category;

ALTER TABLE IF EXISTS product_manager
    ADD CONSTRAINT FKj5iwocb2y5gmy0n04wp6ngeyq
    FOREIGN KEY (category_id)
    REFERENCES product_category;

ALTER TABLE IF EXISTS product_purchase
    ADD CONSTRAINT FKaop2umvfw9dxt3yvfnqcqixss
    FOREIGN KEY (customer_id)
    REFERENCES customer;

ALTER TABLE IF EXISTS product_purchase
    ADD CONSTRAINT FK2ceg0kva32705a9f5q70i3wvq
    FOREIGN KEY (product_id)
    REFERENCES product;