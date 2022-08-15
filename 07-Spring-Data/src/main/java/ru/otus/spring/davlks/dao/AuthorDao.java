package ru.otus.spring.davlks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.davlks.entity.Author;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    Author findById(long id);

    List<Author> findAll();

    Author save(Author author);

    void deleteById(long id);
}
