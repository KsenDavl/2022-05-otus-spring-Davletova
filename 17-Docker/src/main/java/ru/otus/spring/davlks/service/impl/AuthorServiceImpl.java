package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.AuthorDao;
import ru.otus.spring.davlks.entity.Author;
import ru.otus.spring.davlks.entity.Genre;
import ru.otus.spring.davlks.service.AuthorService;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public Author addAuthor(Author author) {
        author = authorDao.save(author);
        return author;
    }

    @Override
    public Author getAuthorById(long id) {
        return authorDao.findById(id).orElseThrow();
    }

    @Override
    public Author getAuthorByFullName(String lastName, String firstName) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        author = authorDao.save(author);
        return author;
    }

    @Override
    public void deleteAuthorById(long id) {
        authorDao.deleteById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    @Override
    public Author getExistingOrAdd(String lastName, String firstName) {
        Optional<Author> author =  authorDao.getAuthorByLastNameAndFirstName(lastName, firstName);
        return author.orElseGet(() -> authorDao.save(new Author(lastName, firstName)));
    }
}
