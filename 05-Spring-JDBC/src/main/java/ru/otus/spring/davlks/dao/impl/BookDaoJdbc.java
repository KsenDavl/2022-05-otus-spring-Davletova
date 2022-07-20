package ru.otus.spring.davlks.dao.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.davlks.dao.BookDao;
import ru.otus.spring.davlks.entity.Author;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDaoJdbc.class);

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Book getBookById(long id) {
        final Map<String, Object> params = Map.of("id", id);
        return jdbc.queryForObject("select b.id, b.title, b.author, b.genre, a.last_name,a.first_name, g.name " +
                "from books b " +
                "left join genres g on b.genre = g.id " +
                "left join authors a on a.id = b.author where b.id = :id", params, new BookMapper());
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbc.query("select b.id, b.title, b.author, b.genre, a.last_name,a.first_name, g.name " +
                "from books b " +
                "left join genres g on b.genre = g.id " +
                "left join authors a on a.id = b.author", new BookMapper());
    }

    @Override
    public Book addBook(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author", book.getAuthor().getId());
        params.addValue("genre", book.getGenre().getId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into books (title, author, genre) values(:title, :author, :genre)", params, keyHolder);
        book.setId((int) keyHolder.getKeys().get("id"));
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        params.put("title", book.getTitle());
        params.put("author", book.getAuthor().getId());
        params.put("genre", book.getGenre().getId());
        jdbc.update("update books set title = :title, author = :author, genre = :genre where id = :id", params);
        return book;
    }

    @Override
    public void deleteBook(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    private class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) {
            Book book = null;

            try {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");

                Long authorId = resultSet.getLong("author");
                String authorLastName = resultSet.getString("last_name");
                String authorFirstName = resultSet.getString("first_name");
                Author author = new Author(authorId, authorLastName, authorFirstName);

                long genreId = resultSet.getLong("genre");
                String genreName = resultSet.getString("name");
                Genre genre = new Genre(genreId, genreName);

                book = new Book(id, title, author, genre);
            } catch (SQLException e) {
                LOGGER.error("Error at getting result of sqlQuery with resultSet = " + resultSet.toString());
            }
            return book;
        }
    }
}
