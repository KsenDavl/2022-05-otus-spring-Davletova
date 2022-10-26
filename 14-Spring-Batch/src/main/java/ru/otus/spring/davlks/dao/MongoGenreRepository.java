package ru.otus.spring.davlks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.davlks.entity.MongoGenre;

public interface MongoGenreRepository extends MongoRepository<MongoGenre, String> {

}
