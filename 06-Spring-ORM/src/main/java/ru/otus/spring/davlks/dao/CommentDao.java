package ru.otus.spring.davlks.dao;

import ru.otus.spring.davlks.entity.Comment;

public interface CommentDao {

    Comment getCommentById(long id);

    Comment addComment(Comment comment);

    Comment updateCommentText(Comment comment);

    void deleteCommentById(long id);
}
