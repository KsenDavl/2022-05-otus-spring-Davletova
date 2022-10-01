package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment();

    List<Comment> getAllBookComments();
}
