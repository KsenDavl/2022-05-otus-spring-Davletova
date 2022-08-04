package ru.otus.spring.davlks.dao;

import ru.otus.spring.davlks.entity.Genre;

import java.util.List;

public interface GenreDao {

    Genre getGenreById(long id);

    List<Genre> getAllGenres();

    Genre addGenre(Genre genre);

    Genre updateGenre(Genre genre);

    void deleteGenreById(long id);
}
