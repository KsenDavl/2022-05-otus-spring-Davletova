package ru.otus.spring.davlks.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.davlks.entity.Comment;

import java.util.List;

@Repository
public interface CommentDao {

    Comment getCommentById(long id);

    List<Comment> getAllCommentsByBookId(long bookId);

    Comment addComment(Comment comment);

    Comment updateCommentText(Comment comment);

    void deleteCommentById(long id);
}
