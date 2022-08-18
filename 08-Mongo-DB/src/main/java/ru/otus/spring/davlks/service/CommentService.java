package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment();

    Comment getCommentById(String id);

    Comment updateCommentText(String id);

    void deleteCommentById(String id);

    List<Comment> getAllBookComments();
}
