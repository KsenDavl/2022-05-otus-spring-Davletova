package ru.otus.spring.davlks.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.spring.davlks.dao.impl.AuthorDaoJpa;
import ru.otus.spring.davlks.entity.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс AuthorDaoJdbc ")
@DataJpaTest
@Import(AuthorDaoJpa.class)
@Sql(scripts = "classpath:author-data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthorDaoJpaTest {

    private static final long EXISTING_AUTHOR_ID = 1;
    private static final int EXPECTED_NUMBER_OF_EXISTING_AUTHORS = 1;

    @Autowired
    private AuthorDaoJpa authorDao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("вставляет запись")
    void shouldInsertAuthorCorrectly() {
        Author insertedAuthor = new Author(0, "Belyaev", "Alexander");
        authorDao.addAuthor(insertedAuthor);
        Author actualAuthor = em.find(Author.class, EXISTING_AUTHOR_ID + 1);
        assertThat(actualAuthor).isNotNull()
                .matches(author -> "Belyaev".equals(author.getLastName()))
                .matches(author -> "Alexander".equals(author.getFirstName()))
                .matches(author -> author.getId() == EXISTING_AUTHOR_ID + 1);
    }

    @Test
    @DisplayName("получает запись по идентификатору")
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = em.find(Author.class, EXISTING_AUTHOR_ID);
        Author actualAuthor = authorDao.getAuthorById(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("получает список всех записей")
    void shouldReturnExpectedAuthorsList() {
        List<Author> actualAuthorsList = authorDao.getAllAuthors();
        assertThat(actualAuthorsList).isNotNull().hasSize(EXPECTED_NUMBER_OF_EXISTING_AUTHORS);
    }

    @Test
    @DisplayName("удаляет запись")
    void shouldDeleteAuthorCorrectly() {
        Author existingAuthor = em.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(existingAuthor).isNotNull();
        em.detach(existingAuthor);

        authorDao.deleteAuthorById(EXISTING_AUTHOR_ID);

        Author deletedAuthor = em.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(deletedAuthor).isNull();
    }

    @Test
    @DisplayName("обновляет запись")
    void shouldReturnUpdatedAuthor() {
        Author existingAuthor = em.find(Author.class, EXISTING_AUTHOR_ID);
        String oldLastName = existingAuthor.getLastName();
        String oldFirstName = existingAuthor.getFirstName();
        em.detach(existingAuthor);

        Author authorToUpdate = new Author(EXISTING_AUTHOR_ID, "Chesterton", "Gilbert");
        authorDao.updateAuthor(authorToUpdate);

        Author updatedAuthor = em.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(updatedAuthor.getLastName()).isNotEqualTo(oldLastName).isEqualTo("Chesterton");
        assertThat(updatedAuthor.getFirstName()).isNotEqualTo(oldFirstName).isEqualTo("Gilbert");
    }





}