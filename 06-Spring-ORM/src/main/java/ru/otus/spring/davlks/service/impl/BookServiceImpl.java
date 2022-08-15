package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.BookDao;
import ru.otus.spring.davlks.entity.Author;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Genre;
import ru.otus.spring.davlks.service.AuthorService;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.ConsoleService;
import ru.otus.spring.davlks.service.GenreService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final ConsoleService consoleService;
    private final AuthorService authorService;
    private final GenreService genreService;

    private final BookDao bookDao;

    @Override
    @Transactional
    public void addBook() {
        consoleService.write("Type the title of the book:");
        String title = consoleService.read();

        Author author = getAuthor();
        Genre genre = getGenre();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);

        book = bookDao.addBook(book);
        consoleService.write("Added: " + book.toString());
    }

    @Override
    public Book getBookById(long id) {
        Book book = bookDao.getBookById(id);
        return book;
    }

    @Override
    @Transactional
    public Book updateBook(long id) {
        Book book = getBookById(id);

        consoleService.write("Type new title of the book:");
        String title = consoleService.read();

        Author author = getAuthor();
        Genre genre = getGenre();

        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book = bookDao.updateBook(book);

        consoleService.write("Updated: " + book.toString());
        return book;
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        bookDao.deleteBook(id);
        consoleService.write("Deleted book with id = " + id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public void writeListOfBooks(List<Book> books) {
        books.forEach(book -> consoleService.write(book.toString()));
    }

    private void printKnownAuthors() {
        authorService.getAllAuthors();
    }

    private Author getChosenAuthor() {
        String response = consoleService.read();
        if ("add".equals(response)) {
            return authorService.addAuthor();
        } else {
            long authorId = Long.parseLong(response);
           return authorService.getAuthorById(authorId);
        }
    }

    private void printKnownGenres() {
        genreService.getAllGenres();
    }

    private Genre getChosenGenre() {
        String response = consoleService.read();
        if ("add".equals(response)) {
            return genreService.addGenre();
        } else {
            long genreId = Long.parseLong(response);
            return genreService.getGenreById(genreId);
        }
    }

    private Author getAuthor() {
        consoleService.write("Choose the author id or type 'add' to add the author if he/she is missing");
        printKnownAuthors();
        return getChosenAuthor();
    }

    private Genre getGenre() {
        consoleService.write("Choose the genre id or type 'add' to add genre if it is missing");
        printKnownGenres();
        return getChosenGenre();
    }
}
