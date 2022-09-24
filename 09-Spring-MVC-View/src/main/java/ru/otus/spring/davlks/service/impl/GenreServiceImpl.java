package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.GenreDao;
import ru.otus.spring.davlks.entity.Genre;
import ru.otus.spring.davlks.service.GenreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public Genre addGenre(Genre genre) {
        return genreDao.save(genre);
    }

    @Override
    public Genre getGenreById(long id) {
        return genreDao.findById(id).orElseThrow();
    }

    @Override
    public Optional<Genre> getGenreByName(String name) {
        return genreDao.getGenreByName(name);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return genreDao.save(genre);
    }

    @Override
    public void deleteGenreById(long id) {
        genreDao.deleteById(id);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    @Override
    public Genre getExistingOrAdd(String name) {
        Optional<Genre> genre =  genreDao.getGenreByName(name);
        return genre.orElseGet(() -> genreDao.save(new Genre(name)));
    }
}
