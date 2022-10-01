package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Book;

import java.util.List;

public interface BookService {

    void addBook();

    Book getBookById(String id);

    Book updateBook(String id);

    Book updateBook(Book book);

    void deleteBookById(String id);

    List<Book> getAllBooks();

    void writeListOfBooks(List<Book> books);
}
