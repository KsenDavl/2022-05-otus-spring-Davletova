package ru.otus.spring.davlks.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.spring.davlks.dao.impl.CommentDaoJpa;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс CommentDaoJpa ")
@DataJpaTest
@Import(CommentDaoJpa.class)
@Sql(scripts = "classpath:comment-data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CommentDaoJpaTest {

    private static final long EXISTING_COMMENT_ID = 1;
    private static final long EXISTING_COMMENT_BOOK_ID = 1;
    private static final String INSERTED_COMMENT_TEXT = "What an amazing book! I really enjoy reading it!";
    private static final int EXPECTED_NUMBER_OF_EXISTING_COMMENTS = 2;
    private static final String UPDATED_COMMENT_TEXT = "Changed my mind. Nothing special...!";

    @Autowired
    private CommentDaoJpa commentDao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("вставляет запись")
    void shouldInsertCommentCorrectly() {
        Comment commentToInsert = new Comment(0, INSERTED_COMMENT_TEXT, em.find(Book.class, EXISTING_COMMENT_BOOK_ID));
        commentDao.addComment(commentToInsert);
        Comment actualComment = em.find(Comment.class, EXISTING_COMMENT_ID + 2);
        assertThat(actualComment).isNotNull()
                .matches(comment -> INSERTED_COMMENT_TEXT.equals(comment.getText()))
                .matches(comment -> comment.getId() == EXISTING_COMMENT_ID + 2);
    }

    @Test
    @DisplayName("получает запись по идентификатору")
    void shouldReturnExpectedCommentById() {
        Comment expectedComment = em.find(Comment.class, EXISTING_COMMENT_ID);
        Comment actualComment = commentDao.getCommentById(EXISTING_COMMENT_ID);
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    @DisplayName("получает список всех комментариев к книге")
    void shouldReturnExpectedCommentsList() {
        List<Comment> actualCommentList = commentDao.getAllCommentsByBookId(EXISTING_COMMENT_BOOK_ID);
        assertThat(actualCommentList).isNotNull().hasSize(EXPECTED_NUMBER_OF_EXISTING_COMMENTS);
    }

    @Test
    @DisplayName("удаляет запись")
    void shouldDeleteCommentCorrectly() {
        Comment existingComment = em.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(existingComment).isNotNull();
        em.detach(existingComment);

        commentDao.deleteCommentById(EXISTING_COMMENT_ID);

        Comment deletedComment = em.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(deletedComment).isNull();
    }

    @Test
    @DisplayName("обновляет запись")
    void shouldReturnUpdatedComment() {
        Comment existingComment = em.find(Comment.class, EXISTING_COMMENT_ID);
        String oldText = existingComment.getText();
        em.detach(existingComment);

        Comment commentToUpdate = new Comment();
        commentToUpdate.setId(EXISTING_COMMENT_ID);
        commentToUpdate.setText(UPDATED_COMMENT_TEXT);
        commentDao.updateCommentText(commentToUpdate);

        Comment updatedComment = em.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(updatedComment.getText()).isNotEqualTo(oldText).isEqualTo(UPDATED_COMMENT_TEXT);
    }
}