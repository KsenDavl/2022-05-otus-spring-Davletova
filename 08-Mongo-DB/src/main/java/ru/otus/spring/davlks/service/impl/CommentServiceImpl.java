package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Comment;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.CommentService;
import ru.otus.spring.davlks.service.ConsoleService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookService bookService;
    private final ConsoleService consoleService;

    @Override
    public void addComment() {
        List<Book> books = bookService.getAllBooks();
        if (books == null || books.isEmpty()) {
           giveNoFoundBookMessage();
           return;
        }
        consoleService.write("What book you want to leave your comment to? Choose and type its id");
        bookService.writeListOfBooks(books);
        String id = consoleService.read();
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        consoleService.write("Leave a comment to the book:");
        Comment comment = new Comment();
        comment.setText(consoleService.read());
        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
        }
        book.getComments().add(comment);
        bookService.updateBook(book);
        consoleService.write("Your comment was added");
    }

    @Override
    public List<Comment> getAllBookComments() {
        List<Book> books = bookService.getAllBooks();
        if (books == null || books.isEmpty()) {
            giveNoFoundBookMessage();
            return null;
        }
        consoleService.write("Choose the book and type its id:");
        bookService.writeListOfBooks(books);
        String id = consoleService.read();
        Book book = bookService.getBookById(id);
        book.getComments().forEach(comment -> consoleService.write(comment.toString()));
        return book.getComments();
    }

    public void giveNoFoundBookMessage() {
        consoleService.write("No books available for comments are found, add a book first");
    }
}
