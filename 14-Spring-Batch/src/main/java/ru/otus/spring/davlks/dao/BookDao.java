package ru.otus.spring.davlks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.davlks.entity.Book;

public interface BookDao extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

}
