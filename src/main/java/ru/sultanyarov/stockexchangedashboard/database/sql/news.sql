CREATE TABLE news
(
    id       SERIAL PRIMARY KEY,
    headline VARCHAR NOT NULL,
    summary  VARCHAR NOT NULL,
    url      VARCHAR NOT NULL,
    source   VARCHAR NOT NULL
);