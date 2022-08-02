package ru.otus.spring.davlks.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.davlks.entity.Book;

import java.util.List;

@Repository
public interface BookDao {

    Book getBookById(long id);

    List<Book> getAllBooks();

    Book addBook(Book book);

    Book updateBook(Book book);

    void deleteBook(long id);
}
