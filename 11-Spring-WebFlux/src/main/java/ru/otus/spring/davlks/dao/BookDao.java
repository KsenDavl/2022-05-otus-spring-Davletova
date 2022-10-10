package ru.otus.spring.davlks.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.davlks.entity.Book;

public interface BookDao extends ReactiveMongoRepository<Book, String> {

    Mono<Book> save(Book book);

    Mono<Book> findById(String id);

    Mono<Void> deleteById(String id);

    Flux<Book> findAll();
}
