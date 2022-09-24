package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.dto.BookDto;
import ru.otus.spring.davlks.entity.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    Book getBookById(long id);

    Book updateBook(Book book);

    void deleteBookById(long id);

    List<Book> getAllBooks();

    Book mapBookDtoToBook(BookDto bookDTO);
}
