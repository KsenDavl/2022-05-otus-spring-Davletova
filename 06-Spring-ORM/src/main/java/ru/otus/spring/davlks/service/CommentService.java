package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment();

    Comment getCommentById(long id);

    Comment updateCommentText(long id);

    void deleteCommentById(long id);

    List<Comment> getAllBookComments();
}
