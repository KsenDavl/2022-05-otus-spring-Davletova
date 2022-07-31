package ru.otus.spring.davlks.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.spring.davlks.dao.impl.GenreDaoJdbc;
import ru.otus.spring.davlks.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс GenreDaoJdbc ")
@JdbcTest
@Import(GenreDaoJdbc.class)
@Sql(scripts = "classpath:genre-data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GenreDaoJdbcTest {

    private static final int EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "drama";

    @Autowired
    private GenreDaoJdbc genreDao;

    @Test
    @DisplayName("вставляет запись")
    void shouldInsertGenreCorrectly() {
        Genre expectedGenre = new Genre();
        expectedGenre.setName("comedy");
        genreDao.addGenre(expectedGenre);
        Genre actualGenre = genreDao.getGenreById(EXISTING_GENRE_ID + 1);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("получает запись по идентификатору")
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreDao.getGenreById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("получает список всех записей")
    void shouldReturnExpectedGenresList() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = genreDao.getAllGenres();
        assertThat(actualGenreList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedGenre);
    }

    @Test
    @DisplayName("удаляет запись")
    void shouldDeleteGenreCorrectly() {
        assertThatCode(() -> genreDao.getGenreById(EXISTING_GENRE_ID))
                .doesNotThrowAnyException();

        genreDao.deleteGenreById(EXISTING_GENRE_ID);

        assertThatThrownBy(() -> genreDao.getGenreById(EXISTING_GENRE_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    @DisplayName("обновляет запись")
    void shouldReturnUpdatedGenre() {
        Genre updatedGenre = new Genre(EXISTING_GENRE_ID, "thriller");
        genreDao.updateGenre(updatedGenre);
        Genre actualGenre = genreDao.getGenreById(EXISTING_GENRE_ID);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(updatedGenre);
    }





}