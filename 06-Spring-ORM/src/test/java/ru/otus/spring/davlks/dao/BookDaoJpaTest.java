package ru.otus.spring.davlks.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.spring.davlks.dao.impl.AuthorDaoJpa;
import ru.otus.spring.davlks.dao.impl.BookDaoJpa;
import ru.otus.spring.davlks.dao.impl.GenreDaoJpa;
import ru.otus.spring.davlks.entity.Author;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс BookDaoJdbc ")
@DataJpaTest
@Import({BookDaoJpa.class, AuthorDaoJpa.class, GenreDaoJpa.class})
@Sql(scripts = "classpath:book-data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookDaoJpaTest {

    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "It";
    private static Author EXISTING_BOOK_AUTHOR;
    private static Genre EXISTING_BOOK_GENRE;

    @Autowired
    private BookDaoJpa bookDao;

    @Autowired
    private TestEntityManager em;

    @BeforeAll
    static void init() {
        EXISTING_BOOK_AUTHOR = new Author();
        EXISTING_BOOK_AUTHOR.setId(1);
        EXISTING_BOOK_AUTHOR.setLastName("Stephen");
        EXISTING_BOOK_AUTHOR.setFirstName("King");

        EXISTING_BOOK_GENRE = new Genre();
        EXISTING_BOOK_GENRE.setId(1L);
        EXISTING_BOOK_GENRE.setName("drama");
    }

    @Test
    @DisplayName("вставляет запись")
    void shouldInsertBookCorrectly() {
        Book expectedBook = new Book();
        expectedBook.setTitle("Duma Key");
        expectedBook.setAuthor(EXISTING_BOOK_AUTHOR);
        expectedBook.setGenre(EXISTING_BOOK_GENRE);

        bookDao.addBook(expectedBook);
        Book actualBook = em.find(Book.class, EXISTING_BOOK_ID + 1);
        assertThat(actualBook).isNotNull().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("получает запись по идентификатору")
    void shouldReturnExpectedBookById() {
        Book expectedBook = em.find(Book.class, EXISTING_BOOK_ID);
        Book actualBook = bookDao.getBookById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("получает список всех записей")
    void shouldReturnExpectedBooksList() {
        Book expectedBook = em.find(Book.class, EXISTING_BOOK_ID);
        List<Book> actualBookList = bookDao.getAllBooks();
        assertThat(actualBookList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

    @Test
    @DisplayName("удаляет запись")
    void shouldDeleteBookCorrectly() {
        Book existingBook = em.find(Book.class, EXISTING_BOOK_ID);
        assertThat(existingBook).isNotNull();
        em.detach(existingBook);

        bookDao.deleteBook(EXISTING_BOOK_ID);

        Book deletedBook = em.find(Book.class, EXISTING_BOOK_ID);
        assertThat(deletedBook).isNull();
    }

    @Test
    @DisplayName("обновляет запись")
    void shouldReturnUpdatedBook() {
        Book existingBook = em.find(Book.class, EXISTING_BOOK_ID);
        em.detach(existingBook);

        Book bookToUpdate = new Book();
        bookToUpdate.setId(EXISTING_BOOK_ID);
        bookToUpdate.setTitle( "Childhood");
        bookToUpdate.setAuthor(EXISTING_BOOK_AUTHOR);
        bookToUpdate.setGenre(EXISTING_BOOK_GENRE);
        bookDao.updateBook(bookToUpdate);

        Book updatedBook = em.find(Book.class, EXISTING_BOOK_ID);
        assertThat(updatedBook.getTitle()).isNotEqualTo(EXISTING_BOOK_TITLE).isEqualTo("Childhood");

    }





}