CREATE TABLE stock
(
    stock_id UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    name     VARCHAR(35)                                 NOT NULL,
    ticker   VARCHAR(10)                                 NOT NULL,
    currency VARCHAR(10)                                 NOT NULL,
    logo     VARCHAR(250)
);

INSERT INTO stock (stock_id, name, ticker, currency, logo)
VALUES ('626b7020-1550-4cf4-9ba4-1e71f4b5c662', 'Apple Inc', 'AAPL', 'USD',
        'https://static2.finnhub.io/file/publicdatany/finnhubimage/stock_logo/AAPL.svg');
