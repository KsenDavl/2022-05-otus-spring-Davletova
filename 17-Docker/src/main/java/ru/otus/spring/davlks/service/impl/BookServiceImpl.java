package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.BookDao;
import ru.otus.spring.davlks.dto.BookDto;
import ru.otus.spring.davlks.entity.Author;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Genre;
import ru.otus.spring.davlks.service.AuthorService;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorService authorService;
    private final GenreService genreService;

    private final BookDao bookDao;

    @Override
    public BookDto saveBook(BookDto bookDto) {
        Book book = bookDao.save(mapBookDtoToBook(bookDto));
        return mapBookToBookDto(book);
    }

    @Override
    public Book getBookById(long id) {
        return bookDao.findById(id).orElseThrow();
    }

    @Override
    public void deleteBookById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public Book mapBookDtoToBook(BookDto bookDTO) {
        Book book = new Book();
        if (bookDTO.getId() != null) {
            book.setId(bookDTO.getId());
        }
        book.setTitle(bookDTO.getTitle());

        Genre genre = genreService.getGenreById(bookDTO.getGenre().getId());
        book.setGenre(genre);

        Author author = authorService.getAuthorById(bookDTO.getAuthor().getId());
        book.setAuthor(author);

        return book;
    }

    @Override
    public BookDto mapBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setGenre(book.getGenre());
        bookDto.setAuthor(book.getAuthor());

        return bookDto;
    }

}
