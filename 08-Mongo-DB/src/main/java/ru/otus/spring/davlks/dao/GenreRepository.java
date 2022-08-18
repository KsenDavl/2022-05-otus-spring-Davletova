package ru.otus.spring.davlks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.davlks.entity.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
