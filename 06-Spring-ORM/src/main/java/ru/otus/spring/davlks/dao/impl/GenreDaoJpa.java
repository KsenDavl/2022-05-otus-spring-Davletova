package ru.otus.spring.davlks.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.davlks.dao.GenreDao;
import ru.otus.spring.davlks.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Genre getGenreById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre addGenre(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Genre updateGenre(Genre genre) {
        Query query = em.createQuery("update Genre g " +
                "set g.name = :name " +
                "where g.id = :id");
        query.setParameter("name", genre.getName());
        query.setParameter("id", genre.getId());
        query.executeUpdate();
        return genre;
    }

    @Override
    public void deleteGenreById(long id) {
        Query query = em.createQuery("delete " +
                "from Genre g " +
                "where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
