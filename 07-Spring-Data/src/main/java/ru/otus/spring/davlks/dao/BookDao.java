package ru.otus.spring.davlks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.davlks.entity.Book;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Book findById(long id);

    List<Book> findAll();

    Book save(Book book);

    void deleteById(long id);
}
