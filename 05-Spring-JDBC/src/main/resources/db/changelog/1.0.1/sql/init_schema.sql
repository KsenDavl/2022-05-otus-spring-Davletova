CREATE SEQUENCE IF not exists genres_id_seq start 1;

create table genres(
                       id   INTEGER NOT NULL DEFAULT nextval('genres_id_seq') PRIMARY KEY,
                       name VARCHAR(100)
);

CREATE SEQUENCE IF not exists authors_id_seq start 1;

CREATE TABLE authors (
                         id         INTEGER NOT NULL DEFAULT nextval('authors_id_seq') PRIMARY KEY,
                         first_name VARCHAR(100),
                         last_name  VARCHAR(100)
);

CREATE SEQUENCE IF not exists books_id_seq start 1;

CREATE TABLE books (
    id INTEGER NOT NULL DEFAULT nextval('books_id_seq') PRIMARY KEY,
    title VARCHAR(100),
    author BIGINT references authors,
    genre BIGINT references genres
    );

