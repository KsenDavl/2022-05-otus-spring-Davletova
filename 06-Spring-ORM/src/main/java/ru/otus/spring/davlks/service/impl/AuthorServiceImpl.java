package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.AuthorDao;
import ru.otus.spring.davlks.entity.Author;
import ru.otus.spring.davlks.service.AuthorService;
import ru.otus.spring.davlks.service.ConsoleService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final ConsoleService consoleService;

    @Override
    @Transactional
    public Author addAuthor() {
        consoleService.write("Type the last name of the author:");
        String lastName = consoleService.read();

        consoleService.write("Type the first name of the author:");
        String firstName = consoleService.read();

        Author author = new Author();
        author.setLastName(lastName);
        author.setFirstName(firstName);
        author = authorDao.addAuthor(author);
        consoleService.write("Added: " + author.toString());
        return author;
    }

    @Override
    public Author getAuthorById(long id) {
        Author author = authorDao.getAuthorById(id);
        consoleService.write("Got: " + author.toString());
        return author;
    }

    @Override
    @Transactional
    public Author updateAuthor(long id) {
        consoleService.write("Type new last name of the author:");
        String lastName = consoleService.read();

        consoleService.write("Type new first name of the author:");
        String firstName = consoleService.read();

        Author author = authorDao.getAuthorById(id);
        author.setLastName(lastName);
        author.setFirstName(firstName);
        author = authorDao.updateAuthor(author);

        consoleService.write("Updated: " + author.toString());
        return author;
    }

    @Override
    @Transactional
    public void deleteAuthorById(long id) {
        authorDao.deleteAuthorById(id);
        consoleService.write("Deleted author with id = " + id);
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorDao.getAllAuthors();
        authors.forEach(author -> consoleService.write(author.toString()));
        return authors;
    }
}
