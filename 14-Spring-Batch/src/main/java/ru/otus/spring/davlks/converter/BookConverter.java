package ru.otus.spring.davlks.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.davlks.dao.AuthorDao;
import ru.otus.spring.davlks.dao.GenreDao;
import ru.otus.spring.davlks.entity.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookConverter {

    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public Author convertAuthor(MongoAuthor mongoAuthor) {
        Optional<Author> optionalAuthor =
                authorDao.findByLastNameAndFirstName(mongoAuthor.getLastName(), mongoAuthor.getFirstName());
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        } else {
            Author author = new Author();
            author.setFirstName(mongoAuthor.getFirstName());
            author.setLastName(mongoAuthor.getLastName());
            return authorDao.save(author);
        }
    }

    public Genre convertGenre(MongoGenre mongoGenre) {
        Optional<Genre> optionalGenre = genreDao.findByName(mongoGenre.getName());
        if (optionalGenre.isPresent()) {
            return optionalGenre.get();
        } else {
            Genre genre = new Genre();
            genre.setName(mongoGenre.getName());
            return genreDao.save(genre);
        }
    }

    public Comment convertComment(MongoComment mongoComment) {
        Comment comment = new Comment();
        comment.setText(mongoComment.getText());
        return comment;
    }

    public Book convertBook(MongoBook mongoBook) {
        Book book = new Book();
        book.setTitle(mongoBook.getTitle());
        book.setAuthor(convertAuthor(mongoBook.getMongoAuthor()));
        book.setGenre(convertGenre(mongoBook.getMongoGenre()));
        if(mongoBook.getMongoComments() != null) {
            List<Comment> comments = mongoBook.getMongoComments().stream()
                    .map(mongoComment -> {
                        Comment com = convertComment(mongoComment);
                        com.setBook(book);
                        return com;
                    }).collect(Collectors.toList());
            book.setComments(comments);
        }
        return book;
    }

}
