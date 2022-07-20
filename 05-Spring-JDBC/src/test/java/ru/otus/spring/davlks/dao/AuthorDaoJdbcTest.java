package ru.otus.spring.davlks.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.spring.davlks.dao.impl.AuthorDaoJdbc;
import ru.otus.spring.davlks.entity.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс AuthorDaoJdbc ")
@JdbcTest
@Import(AuthorDaoJdbc.class)
@Sql(scripts = "classpath:author-data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthorDaoJdbcTest {

    private static final int EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_LAST_NAME = "Stephen";
    private static final String EXISTING_AUTHOR_FIRST_NAME = "King";

    @Autowired
    private AuthorDaoJdbc authorDao;

    @Test
    @DisplayName("вставляет запись")
    void shouldInsertAuthorCorrectly() {
        Author expectedAuthor = new Author();
        expectedAuthor.setLastName("Chekhov");
        expectedAuthor.setFirstName("Anton");
        authorDao.addAuthor(expectedAuthor);
        Author actualAuthor = authorDao.getAuthorById(EXISTING_AUTHOR_ID + 1);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("получает запись по идентификатору")
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_LAST_NAME, EXISTING_AUTHOR_FIRST_NAME);
        Author actualAuthor = authorDao.getAuthorById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("получает список всех записей")
    void shouldReturnExpectedAuthorsList() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_LAST_NAME, EXISTING_AUTHOR_FIRST_NAME);
        List<Author> actualAuthorList = authorDao.getAllAuthors();
        assertThat(actualAuthorList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedAuthor);
    }

    @Test
    @DisplayName("удаляет запись")
    void shouldDeleteAuthorCorrectly() {
        assertThatCode(() -> authorDao.getAuthorById(EXISTING_AUTHOR_ID))
                .doesNotThrowAnyException();

        authorDao.deleteAuthorById(EXISTING_AUTHOR_ID);

        assertThatThrownBy(() -> authorDao.getAuthorById(EXISTING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    @DisplayName("обновляет запись")
    void shouldReturnUpdatedAuthor() {
        Author updatedAuthor = new Author(EXISTING_AUTHOR_ID, "Leontev", "Anton");
        authorDao.updateAuthor(updatedAuthor);
        Author actualAuthor = authorDao.getAuthorById(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(updatedAuthor);
    }





}