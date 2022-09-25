package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Author;

import java.util.List;

public interface AuthorService {

    Author getAuthorById(long id);

    Author getAuthorByFullName(String lastName, String firstName);

    Author saveAuthor(Author author);

    void deleteAuthorById(long id);

    List<Author> getAllAuthors();

    Author getExistingOrAdd(String lastName, String firstName);
}
