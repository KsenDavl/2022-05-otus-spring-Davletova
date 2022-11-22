package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.CommentDao;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Comment;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Override
    public void addComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public Comment getCommentById(long id) {
        return commentDao.findById(id).orElseThrow();
    }

    @Override
    public Comment updateCommentText(Comment comment) {
        commentDao.save(comment);
        return comment;
    }

    @Override
    public void deleteCommentById(long id) {
        commentDao.deleteById(id);
    }

    @Override
    public List<Comment> getAllBookComments(long bookId) {
        return commentDao.findAllByBookId(bookId);
    }
}
