package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre addGenre();

    Genre getGenreById(String id);

    Genre updateGenre(String id);

    void deleteGenreById(String id);

    List<Genre> getAllGenres();
}
