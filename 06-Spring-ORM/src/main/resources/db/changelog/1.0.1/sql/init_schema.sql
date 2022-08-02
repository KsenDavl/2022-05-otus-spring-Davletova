DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);


DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255)
);


DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id     BIGSERIAL PRIMARY KEY,
    title  VARCHAR(255),
    author BIGINT references authors,
    genre  BIGINT references genres
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
    id   BIGSERIAL PRIMARY KEY,
    text TEXT,
    book BIGINT references books
);

