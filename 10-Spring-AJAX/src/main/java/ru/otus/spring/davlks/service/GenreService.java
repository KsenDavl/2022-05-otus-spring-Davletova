package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre getGenreById(long id);

    Optional<Genre> getGenreByName(String name);

    Genre saveGenre(Genre genre);

    void deleteGenreById(long id);

    List<Genre> getAllGenres();

    Genre getExistingOrAdd(String name);
}
