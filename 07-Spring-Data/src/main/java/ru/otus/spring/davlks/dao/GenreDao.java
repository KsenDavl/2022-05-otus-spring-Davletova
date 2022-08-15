package ru.otus.spring.davlks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.davlks.entity.Genre;

import java.util.List;

public interface GenreDao extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {

    Genre findById(long id);

    List<Genre> findAll();

    Genre save(Genre genre);

    void deleteById(long id);
}
