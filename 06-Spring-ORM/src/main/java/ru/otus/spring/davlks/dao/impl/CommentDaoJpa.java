package ru.otus.spring.davlks.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.davlks.dao.CommentDao;
import ru.otus.spring.davlks.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Comment getCommentById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public Comment addComment(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Comment updateCommentText(Comment comment) {
       em.merge(comment);
        return comment;
    }

    @Override
    public void deleteCommentById(long id) {
      Comment comment = em.find(Comment.class, id);
      em.remove(comment);
    }

}
