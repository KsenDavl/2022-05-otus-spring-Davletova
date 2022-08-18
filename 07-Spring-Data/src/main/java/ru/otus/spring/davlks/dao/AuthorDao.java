package ru.otus.spring.davlks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.davlks.entity.Author;

public interface AuthorDao extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

}
