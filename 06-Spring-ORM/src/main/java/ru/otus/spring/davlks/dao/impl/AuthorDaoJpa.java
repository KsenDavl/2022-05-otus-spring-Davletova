package ru.otus.spring.davlks.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.davlks.dao.AuthorDao;
import ru.otus.spring.davlks.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Author getAuthorById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAllAuthors() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Author addAuthor(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Author updateAuthor(Author author) {
        Query query = em.createQuery("update Author a " +
                "set a.lastName = :lastName, " +
                "a.firstName = :firstName " +
                "where a.id = :id");
        query.setParameter("lastName", author.getLastName());
        query.setParameter("firstName", author.getFirstName());
        query.setParameter("id", author.getId());
        query.executeUpdate();
        return author;
    }

    @Override
    public void deleteAuthorById(long id) {
        Query query = em.createQuery("delete " +
                "from Author a " +
                "where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
