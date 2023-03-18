CREATE TABLE user_stock
(
    user_id  UUID REFERENCES user_table (user_id) ON UPDATE CASCADE,
    stock_id UUID REFERENCES stock (stock_id) ON UPDATE CASCADE,
    CONSTRAINT user_stock_pkey PRIMARY KEY (user_id, stock_id)
);

INSERT INTO user_stock (user_id, stock_id)
VALUES ('00000000-0000-0000-0000-000000000000', '626b7020-1550-4cf4-9ba4-1e71f4b5c662');
