package ru.otus.spring.davlks.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.davlks.entity.Genre;

import java.util.List;

@Repository
public interface GenreDao {

    Genre getGenreById(long id);

    List<Genre> getAllGenres();

    Genre addGenre(Genre genre);

    Genre updateGenre(Genre genre);

    void deleteGenreById(long id);
}
