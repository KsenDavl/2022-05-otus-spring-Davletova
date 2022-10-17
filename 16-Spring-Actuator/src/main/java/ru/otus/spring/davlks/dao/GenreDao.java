package ru.otus.spring.davlks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.davlks.entity.Genre;

import java.util.Optional;

public interface GenreDao extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {

    Optional<Genre> getGenreByName(String name);
}
