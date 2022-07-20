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
import ru.otus.spring.davlks.dao.AuthorDao;
import ru.otus.spring.davlks.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDaoJdbc.class);

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Author getAuthorById(long id) {
        final Map<String, Object> params = Map.of("id", id);
        return jdbc.queryForObject("select id, last_name, first_name from authors where id = :id", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAllAuthors() {
        return jdbc.query("select id, last_name, first_name from authors", new AuthorMapper());
    }

    @Override
    public Author addAuthor(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("lastName", author.getLastName());
        params.addValue("firstName", author.getFirstName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into authors (last_name, first_name) values(:lastName, :firstName)", params, keyHolder);
        author.setId((int) keyHolder.getKeys().get("id"));
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", author.getId());
        params.put("lastName", author.getLastName());
        params.put("firstName", author.getFirstName());
        jdbc.update("update authors set last_name = :lastName, first_name = :firstName where id = :id", params);
        return author;
    }

    @Override
    public void deleteAuthorById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("delete from authors where id = :id", params);
    }


    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) {
           Author author = null;

            try {
                long id = resultSet.getLong("id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                author = new Author(id, lastName, firstName);
            } catch (SQLException e) {
                LOGGER.error("Error at getting result of sqlQuery with resultSet = " + resultSet.toString());
            }
            return author;
        }
    }
}
