package ru.otus.spring.davlks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.davlks.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
