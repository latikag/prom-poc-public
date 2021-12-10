CREATE TABLE product(
    id SERIAL ,
    name varchar(255),
    price double precision,
    time TIMESTAMPTZ       NOT NULL
);