package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.CommentRepository;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Comment;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.CommentService;
import ru.otus.spring.davlks.service.ConsoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentrepository;
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
        String name = consoleService.read();

        Comment comment = new Comment();
        comment.setText(name);
        comment.setBook(book);
        comment = commentrepository.save(comment);
        consoleService.write("Added: " + comment.toString());
    }

    @Override
    public Comment getCommentById(String id) {
        Comment comment = commentrepository.findById(id).orElseThrow();
        consoleService.write("Got: " + comment.toString());
        return comment;
    }

    @Override
    public Comment updateCommentText(String id) {
        consoleService.write("Type new text of the comment:");
        String text = consoleService.read();

        Comment comment = commentrepository.findById(id).orElseThrow();
        comment.setText(text);
        comment = commentrepository.save(comment);

        consoleService.write("Updated: " + comment.toString());
        return comment;
    }

    @Override
    public void deleteCommentById(String id) {
        commentrepository.deleteById(id);
        consoleService.write("Deleted comment with id = " + id);
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
        List<Comment> comments = commentrepository.findAllByBook_Id(book.getId());
        comments.forEach(comment -> consoleService.write(comment.toString()));
        return comments;
    }

    public void giveNoFoundBookMessage() {
        consoleService.write("No books available for comments are found, add a book first");
    }
}
