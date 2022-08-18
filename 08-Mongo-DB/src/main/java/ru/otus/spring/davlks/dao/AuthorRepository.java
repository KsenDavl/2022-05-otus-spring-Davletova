package ru.otus.spring.davlks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.davlks.entity.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
