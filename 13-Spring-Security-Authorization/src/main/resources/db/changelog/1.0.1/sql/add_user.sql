DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255) NOT NULL
);