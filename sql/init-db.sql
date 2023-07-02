CREATE TABLE customer
(
    id   SERIAL PRIMARY KEY,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    email varchar NOT NULL
);
INSERT INTO customer(first_name, last_name, email) VALUES ('user 1', 'user 1-1', 'user@user.user');
INSERT INTO customer(first_name, last_name, email) VALUES ('user 2', 'user 2-2', 'user2@user.user');
