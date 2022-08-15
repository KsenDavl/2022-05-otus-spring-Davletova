package ru.otus.spring.davlks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.davlks.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    Comment findById(long id);

    Comment save(Comment comment);

    void deleteById(long id);
}
