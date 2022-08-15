package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.CommentDao;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Comment;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.CommentService;
import ru.otus.spring.davlks.service.ConsoleService;

import javax.transaction.Transactional;
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
        comment = commentDao.save(comment);
        consoleService.write("Added: " + comment.toString());
    }

    @Override
    public Comment getCommentById(long id) {
        Comment comment = commentDao.findById(id);
        consoleService.write("Got: " + comment.toString());
        return comment;
    }

    @Override
    @Transactional
    public Comment updateCommentText(long id) {
        consoleService.write("Type new text of the comment:");
        String text = consoleService.read();

        Comment comment = commentDao.findById(id);
        comment.setText(text);
        comment = commentDao.save(comment);

        consoleService.write("Updated: " + comment.toString());
        return comment;
    }

    @Override
    @Transactional
    public void deleteCommentById(long id) {
        commentDao.deleteById(id);
        consoleService.write("Deleted comment with id = " + id);
    }

    @Override
    @javax.transaction.Transactional
    public List<Comment> getAllBookComments() {
        List<Book> books = bookService.getAllBooks();
        if (books == null || books.isEmpty()) {
            giveNoFoundBookMessage();
            return null;
        }
        consoleService.write("Choose the book and type its id:");
        bookService.writeListOfBooks(books);
        long id = Long.parseLong(consoleService.read());
        Book book = bookService.getBookById(id);
        List<Comment> comments = book.getComments();
        comments.forEach(comment -> consoleService.write(comment.toString()));
        return comments;
    }

    public void giveNoFoundBookMessage() {
        consoleService.write("No books available for comments are found, add a book first");
    }
}
