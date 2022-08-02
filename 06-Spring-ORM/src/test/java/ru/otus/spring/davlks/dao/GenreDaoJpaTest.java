package ru.otus.spring.davlks.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.spring.davlks.dao.impl.GenreDaoJpa;
import ru.otus.spring.davlks.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс GenreDaoJpa ")
@DataJpaTest
@Import(GenreDaoJpa.class)
@Sql(scripts = "classpath:genre-data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GenreDaoJpaTest {

    private static final long EXISTING_GENRE_ID = 1;
    private static final int EXPECTED_NUMBER_OF_EXISTING_GENRES = 1;

    @Autowired
    private GenreDaoJpa genreDao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("вставляет запись")
    void shouldInsertGenreCorrectly() {
        Genre insertedAuthor = new Genre(0, "comedy");
        genreDao.addGenre(insertedAuthor);
        Genre actualGenre = em.find(Genre.class, EXISTING_GENRE_ID + 1);
        assertThat(actualGenre).isNotNull()
                .matches(genre -> "comedy".equals(genre.getName()))
                .matches(genre -> genre.getId() == EXISTING_GENRE_ID + 1);
    }

    @Test
    @DisplayName("получает запись по идентификатору")
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = em.find(Genre.class, EXISTING_GENRE_ID);
        Genre actualGenre = genreDao.getGenreById(EXISTING_GENRE_ID);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("получает список всех записей")
    void shouldReturnExpectedGenresList() {
        List<Genre> actualGenreList = genreDao.getAllGenres();
        assertThat(actualGenreList).isNotNull().hasSize(EXPECTED_NUMBER_OF_EXISTING_GENRES);
    }

    @Test
    @DisplayName("удаляет запись")
    void shouldDeleteGenreCorrectly() {
        Genre existingGenre = em.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(existingGenre).isNotNull();
        em.detach(existingGenre);

        genreDao.deleteGenreById(EXISTING_GENRE_ID);

        Genre deletedGenre = em.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(deletedGenre).isNull();
    }

    @Test
    @DisplayName("обновляет запись")
    void shouldReturnUpdatedGenre() {
        Genre existingGenre = em.find(Genre.class, EXISTING_GENRE_ID);
        String oldName = existingGenre.getName();
        em.detach(existingGenre);

        Genre genreToUpdate = new Genre(EXISTING_GENRE_ID, "thriller");
        genreDao.updateGenre(genreToUpdate);

        Genre updatedGenre = em.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(updatedGenre.getName()).isNotEqualTo(oldName).isEqualTo("thriller");
    }

}