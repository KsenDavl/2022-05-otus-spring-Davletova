package ru.otus.spring.davlks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.davlks.entity.MongoBook;

public interface MongoBookRepository extends MongoRepository<MongoBook, String> {
}
