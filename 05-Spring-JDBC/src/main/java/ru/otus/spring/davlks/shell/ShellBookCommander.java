package ru.otus.spring.davlks.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.davlks.service.AuthorService;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.ConsoleService;
import ru.otus.spring.davlks.service.GenreService;

@ShellComponent
@AllArgsConstructor
class ShellBookCommander {

    BookService bookService;
    AuthorService authorService;
    GenreService genreService;

    ConsoleService consoleService;

    @ShellMethod(value = "Get book/author/title by id", key = {"g", "get"})
    public void get(String targetObject, long id) {
        if ("book".equals(targetObject)) {
            bookService.getBookById(id);
        } else if ("author".equals(targetObject)) {
            authorService.getAuthorById(id);
        } else
            if ("genre".equals(targetObject)) {
            genreService.getGenreById(id);
        } else {
            consoleService.write("Command should look like 'get book 3'");
        }

    }

    @ShellMethod(value = "Add book/author/title to library", key = {"a", "add"})
    public void add(String targetObject) {
        if ("book".equals(targetObject)) {
            bookService.addBook();
        } else if ("author".equals(targetObject)) {
            authorService.addAuthor();
        } else if ("genre".equals(targetObject)) {
            genreService.addGenre();
        } else {
            consoleService.write("Command should look like 'add book'");
        }
    }

    @ShellMethod(value = "Delete book/author/genre by id", key = {"d", "delete"})
    public void delete(String targetObject, long id) {
        if ("book".equals(targetObject)) {
            bookService.deleteBookById(id);
        } else if ("author".equals(targetObject)) {
            authorService.deleteAuthorById(id);
        } else if ("genre".equals(targetObject)) {
            genreService.deleteGenreById(id);
        } else {
            consoleService.write("Command should look like 'delete book 4'");
        }
    }

    @ShellMethod(value = "Update book/author/genre info", key = {"u", "update"})
    public void update(String targetObject, long id) {
        if ("book".equals(targetObject)) {
            bookService.updateBook(id);
        } else if ("author".equals(targetObject)) {
            authorService.updateAuthor(id);
        } else if ("genre".equals(targetObject)) {
            genreService.updateGenre(id);
        } else {
            consoleService.write("Command should look like 'update book 5'");
        }
    }

    @ShellMethod(value = "Get all books/authors/genres", key = {"all"})
    public void getAll(String targetObject) {
        if ("book".equals(targetObject)) {
            bookService.getAllBooks();
        } else if ("author".equals(targetObject)) {
            authorService.getAllAuthors();
        } else if ("genre".equals(targetObject)) {
            genreService.getAllGenres();
        } else {
            consoleService.write("Command should look like 'all book'");
        }
    }


}
