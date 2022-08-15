package ru.otus.spring.davlks.dao;

import ru.otus.spring.davlks.entity.Author;

import java.util.List;

public interface AuthorDao {

    Author getAuthorById(long id);

    List<Author> getAllAuthors();

    Author addAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(long id);
}
