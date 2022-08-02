package ru.otus.spring.davlks.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.service.*;

import java.util.List;

@ShellComponent
@AllArgsConstructor
class ShellBookCommander {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    private final ConsoleService consoleService;

    @ShellMethod(value = "Get book/author/title by id", key = {"g", "get"})
    public void get(String targetObject, long id) {
        if ("book".equals(targetObject)) {
            bookService.getBookById(id);
        } else if ("author".equals(targetObject)) {
            authorService.getAuthorById(id);
        } else if ("genre".equals(targetObject)) {
            genreService.getGenreById(id);
        } else if ("comment".equals(targetObject)) {
            commentService.getCommentById(id);
        }else {
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
        } else if ("comment".equals(targetObject)) {
            commentService.addComment();
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
        } else if ("comment".equals(targetObject)) {
            commentService.deleteCommentById(id);
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
        } else if ("comment".equals(targetObject)) {
            commentService.updateCommentText(id);
        } else {
            consoleService.write("Command should look like 'update book 5'");
        }
    }

    @ShellMethod(value = "Get all books/authors/genres", key = {"all"})
    public void getAll(String targetObject) {
        if ("book".equals(targetObject)) {
            List<Book> books = bookService.getAllBooks();
            bookService.writeListOfBooks(books);
        } else if ("author".equals(targetObject)) {
            authorService.getAllAuthors();
        } else if ("genre".equals(targetObject)) {
            genreService.getAllGenres();
        } else if ("comment".equals(targetObject)) {
            commentService.getAllBookComments();
        } else {
            consoleService.write("Command should look like 'all book'");
        }
    }


}
