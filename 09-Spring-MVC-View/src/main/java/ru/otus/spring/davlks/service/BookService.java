package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.dto.BookDto;
import ru.otus.spring.davlks.entity.Book;

import java.util.List;

public interface BookService {

    BookDto saveBook(BookDto bookDto);

    Book getBookById(long id);

    void deleteBookById(long id);

    List<Book> getAllBooks();

    Book mapBookDtoToBook(BookDto bookDTO);

    BookDto mapBookToBookDto(Book book);
}
