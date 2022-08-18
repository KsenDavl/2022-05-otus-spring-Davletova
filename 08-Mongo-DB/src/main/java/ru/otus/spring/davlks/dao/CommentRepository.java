package ru.otus.spring.davlks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.davlks.entity.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBook_Id(String bookId);

}
