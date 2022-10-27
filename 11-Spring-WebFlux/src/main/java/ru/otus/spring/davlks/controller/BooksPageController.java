package ru.otus.spring.davlks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import ru.otus.spring.davlks.dao.BookDao;
import ru.otus.spring.davlks.entity.Book;

@Controller
public class BooksPageController {

    private final BookDao bookDao;

    public BooksPageController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/")
    public String listPage() {
        return "list";
    }

    @GetMapping("/save")
    public String saveBook(Model model) {
        model.addAttribute("book", new Book());
        return "save";
    }

    @GetMapping("/save/{id}")
    public Mono<String> saveBook(@PathVariable String id, Model model) {
        return bookDao.findById(id)
                .map(book -> {
                    model.addAttribute("book", book);
                    return "save";
                });
    }

}