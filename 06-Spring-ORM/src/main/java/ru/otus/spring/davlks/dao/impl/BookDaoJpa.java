package ru.otus.spring.davlks.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.davlks.dao.BookDao;
import ru.otus.spring.davlks.entity.Book;

import javax.persistence.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
@NamedEntityGraph(name = "otus-student-avatars-entity-graph",
        attributeNodes = {@NamedAttributeNode("avatar")})
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book getBookById(long id) {
       return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book addBook(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Book updateBook(Book book) {
        Query query = em.createQuery("update Book b " +
                "set b.title = :title, " +
                "b.author.id = :author, " +
                "b.genre.id = :genre " +
                "where b.id = :id");
        query.setParameter("title", book.getTitle());
        query.setParameter("author", book.getAuthor().getId());
        query.setParameter("genre", book.getGenre().getId());
        query.setParameter("id", book.getId());
        query.executeUpdate();
        return book;
    }

    @Override
    public void deleteBook(long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
