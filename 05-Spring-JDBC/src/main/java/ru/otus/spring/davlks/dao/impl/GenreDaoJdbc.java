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
import ru.otus.spring.davlks.dao.GenreDao;
import ru.otus.spring.davlks.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDaoJdbc.class);

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Genre getGenreById(long id) {
        final Map<String, Object> params = Map.of("id", id);
        return jdbc.queryForObject("select id, name from genres where id = :id", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAllGenres() {
        return jdbc.query("select id, name from genres", new GenreMapper());
    }

    @Override
    public Genre addGenre(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into genres (name) values(:name)", params, keyHolder);
        genre.setId((int) keyHolder.getKeys().get("id"));
        return genre;
    }

    @Override
    public Genre updateGenre(Genre genre) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", genre.getName());
        params.put("id", genre.getId());
        jdbc.update("update genres set name = :name where id = :id", params);
        return genre;
    }

    @Override
    public void deleteGenreById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("delete from genres where id = :id", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) {
            Genre genre = null;

            try {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                genre = new Genre(id, name);
            } catch (SQLException e) {
                LOGGER.error("Error at getting result of sqlQuery with resultSet = " + resultSet.toString());
            }
            return genre;
        }
    }
}
