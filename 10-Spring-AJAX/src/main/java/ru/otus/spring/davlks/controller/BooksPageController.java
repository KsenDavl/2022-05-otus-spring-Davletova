package ru.otus.spring.davlks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring.davlks.dto.BookDto;
import ru.otus.spring.davlks.service.BookService;

@Controller
public class BooksPageController {

    private final BookService bookService;

    public BooksPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String listPage() {
        return "list";
    }

    @GetMapping("book/save")
    public String saveBook(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("bookDto", bookDto);
        return "save";
    }

    @GetMapping("book/save/{id}")
    public String saveBook(@PathVariable Long id, Model model) {
        BookDto bookDto = bookService.mapBookToBookDto(bookService.getBookById(id));
        model.addAttribute("bookDto", bookDto);
        return "save";
    }
}