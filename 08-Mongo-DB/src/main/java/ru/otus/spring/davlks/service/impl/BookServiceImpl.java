package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.BookRepository;
import ru.otus.spring.davlks.entity.Author;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Genre;
import ru.otus.spring.davlks.service.AuthorService;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.ConsoleService;
import ru.otus.spring.davlks.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final ConsoleService consoleService;
    private final AuthorService authorService;
    private final GenreService genreService;

    private final BookRepository bookRepository;

    @Override
    public void addBook() {
        consoleService.write("Type the title of the book:");
        String title = consoleService.read();

        Author author = getAuthor();
        Genre genre = getGenre();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);

        book = bookRepository.save(book);
        consoleService.write("Added: " + book.toString());
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book updateBook(String id) {
        Book book = getBookById(id);

        consoleService.write("Type new title of the book:");
        String title = consoleService.read();

        Author author = getAuthor();
        Genre genre = getGenre();

        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book = bookRepository.save(book);

        consoleService.write("Updated: " + book.toString());
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
        consoleService.write("Deleted book with id = " + id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
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
            String authorId = response;
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
            String genreId = response;
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
