package ru.otus.spring.davlks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.davlks.entity.Author;

import java.util.Optional;

public interface AuthorDao extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    Optional<Author> getAuthorByLastNameAndFirstName(String lastName, String FirstName);
}
