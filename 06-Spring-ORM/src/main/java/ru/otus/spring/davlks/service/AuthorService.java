package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Author;

import java.util.List;

public interface AuthorService {

    Author addAuthor();

    Author getAuthorById(long id);

    Author updateAuthor(long id);

    void deleteAuthorById(long id);

    List<Author> getAllAuthors();
}
