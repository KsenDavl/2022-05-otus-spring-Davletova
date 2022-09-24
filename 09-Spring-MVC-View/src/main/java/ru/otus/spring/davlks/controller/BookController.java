package ru.otus.spring.davlks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.davlks.dto.BookDto;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public String getAllBooks(ModelMap model) {
        List<Book> books  = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/update")
    public String updateBook(@RequestParam Long id, Model model) {
        Book book  = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "update";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") BookDto bookDto) {
        Book book = bookService.mapBookDtoToBook(bookDto);
        bookService.updateBook(book);
        return  "redirect:/book/all";
    }

    @GetMapping("/add")
    public String addBook() {
        return "add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") BookDto bookDto) {
        Book book = bookService.mapBookDtoToBook(bookDto);
        bookService.addBook(book);
        return "redirect:/book/all";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.deleteBookById(id);
        return "redirect:/book/all";
    }

}
