package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Author;

import java.util.List;

public interface AuthorService {

    Author addAuthor();

    Author getAuthorById(String id);

    Author updateAuthor(String id);

    void deleteAuthorById(String id);

    List<Author> getAllAuthors();
}
