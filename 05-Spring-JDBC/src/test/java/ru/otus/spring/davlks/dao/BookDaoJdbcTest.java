package ru.otus.spring.davlks.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.spring.davlks.dao.impl.AuthorDaoJdbc;
import ru.otus.spring.davlks.dao.impl.BookDaoJdbc;
import ru.otus.spring.davlks.dao.impl.GenreDaoJdbc;
import ru.otus.spring.davlks.entity.Author;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс BookDaoJdbc ")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
@Sql(scripts = "classpath:book-data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookDaoJdbcTest {

    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "It";
    private static Author EXISTING_BOOK_AUTHOR;
    private static Genre EXISTING_BOOK_GENRE;

    @Autowired
    private BookDaoJdbc bookDao;

    @BeforeAll
    static void init() {
        EXISTING_BOOK_AUTHOR = new Author();
        EXISTING_BOOK_AUTHOR.setId(1);
        EXISTING_BOOK_AUTHOR.setLastName("Stephen");
        EXISTING_BOOK_AUTHOR.setFirstName("King");

        EXISTING_BOOK_GENRE = new Genre();
        EXISTING_BOOK_GENRE.setId(1);
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
        Book actualBook = bookDao.getBookById(EXISTING_BOOK_ID + 1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("получает запись по идентификатору")
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        Book actualBook = bookDao.getBookById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("получает список всех записей")
    void shouldReturnExpectedBooksList() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        List<Book> actualBookList = bookDao.getAllBooks();
        assertThat(actualBookList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

    @Test
    @DisplayName("удаляет запись")
    void shouldDeleteBookCorrectly() {
        assertThatCode(() -> bookDao.getBookById(EXISTING_BOOK_ID))
                .doesNotThrowAnyException();

        bookDao.deleteBook(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDao.getBookById(EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    @DisplayName("обновляет запись")
    void shouldReturnUpdatedBook() {
        Book updatedBook = new Book(EXISTING_BOOK_ID, "Childhood", EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        bookDao.updateBook(updatedBook);
        Book actualBook = bookDao.getBookById(EXISTING_BOOK_ID);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(updatedBook);
    }





}