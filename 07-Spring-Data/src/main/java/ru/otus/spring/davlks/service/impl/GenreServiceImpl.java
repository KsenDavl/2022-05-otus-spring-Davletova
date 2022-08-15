package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.GenreDao;
import ru.otus.spring.davlks.entity.Genre;
import ru.otus.spring.davlks.service.ConsoleService;
import ru.otus.spring.davlks.service.GenreService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final ConsoleService consoleService;

    @Override
    @Transactional
    public Genre addGenre() {
        consoleService.write("Type the name of the genre:");
        String name = consoleService.read();

        Genre genre = new Genre();
        genre.setName(name);
        genre = genreDao.save(genre);
        consoleService.write("Added: " + genre.toString());
        return genre;
    }

    @Override
    public Genre getGenreById(long id) {
        Genre genre = genreDao.findById(id);
        consoleService.write("Got: " + genre.toString());
        return genre;
    }

    @Override
    @Transactional
    public Genre updateGenre(long id) {

        consoleService.write("Type new name of the genre:");
        String name = consoleService.read();

        Genre genre  = genreDao.findById(id);
        genre.setName(name);
        genre = genreDao.save(genre);

        consoleService.write("Updated: " + genre.toString());
        return genre;
    }

    @Override
    @Transactional
    public void deleteGenreById(long id) {
        genreDao.deleteById(id);
        consoleService.write("Deleted genre with id = " + id);
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = genreDao.findAll();
        genres.forEach(genre -> consoleService.write(genre.toString()));
        return genres;
    }
}
