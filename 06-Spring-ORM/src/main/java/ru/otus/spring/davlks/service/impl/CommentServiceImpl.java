package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.davlks.dao.CommentDao;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Comment;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.ConsoleService;
import ru.otus.spring.davlks.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final BookService bookService;
    private final ConsoleService consoleService;

    @Override
    @Transactional
    public void addComment() {
        List<Book> books = bookService.getAllBooks();
        if (books == null || books.isEmpty()) {
           giveNoFoundBookMessage();
           return;
        }
        consoleService.write("What book you want to leave your comment to? Choose and type its id");
        bookService.writeListOfBooks(books);
        long id = Long.parseLong(consoleService.read());
        Book book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(RuntimeException::new);
        consoleService.write("Leave a comment to the book:");
        String name = consoleService.read();

        Comment comment = new Comment();
        comment.setText(name);
        comment.setBook(book);
        comment = commentDao.addComment(comment);
        consoleService.write("Added: " + comment.toString());
    }

    @Override
    public Comment getCommentById(long id) {
        Comment comment = commentDao.getCommentById(id);
        consoleService.write("Got: " + comment.toString());
        return comment;
    }

    @Override
    @Transactional
    public Comment updateCommentText(long id) {
        consoleService.write("Type new text of the comment:");
        String text = consoleService.read();

        Comment comment  = new Comment();
        comment.setId(id);
        comment.setText(text);
        comment = commentDao.updateCommentText(comment);

        consoleService.write("Updated: " + comment.toString());
        return comment;
    }

    @Override
    @Transactional
    public void deleteCommentById(long id) {
        commentDao.deleteCommentById(id);
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
        long id = Long.parseLong(consoleService.read());
        List<Comment> comments = commentDao.getAllCommentsByBookId(id);
        comments.forEach(comment -> consoleService.write(comment.toString()));
        return comments;
    }

    public void giveNoFoundBookMessage() {
        consoleService.write("No books available for comments are found, add a book first");
    }
}
