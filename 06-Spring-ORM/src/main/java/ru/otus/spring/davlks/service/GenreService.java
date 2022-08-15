package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre addGenre();

    Genre getGenreById(long id);

    Genre updateGenre(long id);

    void deleteGenreById(long id);

    List<Genre> getAllGenres();
}
