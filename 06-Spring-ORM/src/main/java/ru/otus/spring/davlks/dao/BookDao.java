package ru.otus.spring.davlks.dao;

import ru.otus.spring.davlks.entity.Book;

import java.util.List;

public interface BookDao {

    Book getBookById(long id);

    List<Book> getAllBooks();

    Book addBook(Book book);

    Book updateBook(Book book);

    void deleteBook(long id);
}
