package ru.otus.spring.davlks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.davlks.entity.MongoAuthor;

public interface MongoAuthorRepository extends MongoRepository<MongoAuthor, String> {

}
