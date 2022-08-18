package ru.otus.spring.davlks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.dao.AuthorRepository;
import ru.otus.spring.davlks.entity.Author;
import ru.otus.spring.davlks.service.AuthorService;
import ru.otus.spring.davlks.service.ConsoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ConsoleService consoleService;

    @Override
    public Author addAuthor() {
        consoleService.write("Type the last name of the author:");
        String lastName = consoleService.read();

        consoleService.write("Type the first name of the author:");
        String firstName = consoleService.read();

        Author author = new Author();
        author.setLastName(lastName);
        author.setFirstName(firstName);
        author = authorRepository.save(author);
        consoleService.write("Added: " + author.toString());
        return author;
    }

    @Override
    public Author getAuthorById(String id) {
        Author author = authorRepository.findById(id).orElseThrow();
        consoleService.write("Got: " + author.toString());
        return author;
    }

    @Override
    public Author updateAuthor(String id) {
        consoleService.write("Type new last name of the author:");
        String lastName = consoleService.read();

        consoleService.write("Type new first name of the author:");
        String firstName = consoleService.read();

        Author author = authorRepository.findById(id).orElseThrow();
        author.setLastName(lastName);
        author.setFirstName(firstName);
        author = authorRepository.save(author);

        consoleService.write("Updated: " + author.toString());
        return author;
    }

    @Override
    public void deleteAuthorById(String id) {
        authorRepository.deleteById(id);
        consoleService.write("Deleted author with id = " + id);
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(author -> consoleService.write(author.toString()));
        return authors;
    }
}
