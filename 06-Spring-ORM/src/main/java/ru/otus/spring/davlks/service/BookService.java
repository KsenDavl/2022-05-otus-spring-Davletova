package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Book;

import java.util.List;

public interface BookService {

    void addBook();

    Book getBookById(long id);

    Book updateBook(long id);

    void deleteBookById(long id);

    List<Book> getAllBooks();

    void writeListOfBooks(List<Book> books);
}
