package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.GenreRepository;
import ru.otus.spring.davlks.entity.Genre;
import ru.otus.spring.davlks.service.ConsoleService;
import ru.otus.spring.davlks.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genrerepository;
    private final ConsoleService consoleService;

    @Override
    public Genre addGenre() {
        consoleService.write("Type the name of the genre:");
        String name = consoleService.read();

        Genre genre = new Genre();
        genre.setName(name);
        genre = genrerepository.save(genre);
        consoleService.write("Added: " + genre.toString());
        return genre;
    }

    @Override
    public Genre getGenreById(String id) {
        Genre genre = genrerepository.findById(id).orElseThrow();
        consoleService.write("Got: " + genre.toString());
        return genre;
    }

    @Override
    public Genre updateGenre(String id) {

        consoleService.write("Type new name of the genre:");
        String name = consoleService.read();

        Genre genre  = genrerepository.findById(id).orElseThrow();
        genre.setName(name);
        genre = genrerepository.save(genre);

        consoleService.write("Updated: " + genre.toString());
        return genre;
    }

    @Override
    public void deleteGenreById(String id) {
        genrerepository.deleteById(id);
        consoleService.write("Deleted genre with id = " + id);
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = genrerepository.findAll();
        genres.forEach(genre -> consoleService.write(genre.toString()));
        return genres;
    }
}
