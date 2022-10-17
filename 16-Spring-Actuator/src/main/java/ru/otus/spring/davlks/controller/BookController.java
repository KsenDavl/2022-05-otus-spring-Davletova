package ru.otus.spring.davlks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.davlks.dto.BookDto;
import ru.otus.spring.davlks.service.AuthorService;
import ru.otus.spring.davlks.service.BookService;
import ru.otus.spring.davlks.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {

    Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    public BookController(BookService bookService, GenreService genreService, AuthorService authorService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public String getAllBooks(ModelMap model) {
        List<BookDto> books = bookService.getAllBooks().stream()
                .map(bookService::mapBookToBookDto).collect(Collectors.toList());
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/save")
    public String saveBook(Model model) {
        BookDto bookDto = new BookDto();

        model.addAttribute("bookDto", bookDto);
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "save";
    }

    @GetMapping("/save/{id}")
    public String saveBook(@PathVariable Long id, Model model) {
        BookDto bookDto = bookService.mapBookToBookDto(bookService.getBookById(id));

        model.addAttribute("bookDto", bookDto);
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "save";
    }

    @PostMapping("/save")
    public String saveBook(BookDto bookDto) {
        bookService.saveBook(bookDto);
        LOGGER.info("Book saved: " + bookDto.toString());
        return "redirect:/book/all";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.deleteBookById(id);
        LOGGER.info(String.format("Book with id = %d  deleted", id));
        return "redirect:/book/all";
    }

}
