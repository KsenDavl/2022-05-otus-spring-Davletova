package ru.otus.spring.davlks.restController;

import org.springframework.web.bind.annotation.*;
import ru.otus.spring.davlks.dto.BookDto;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.service.BookService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class RestBookController {

    private final BookService bookService;

    public RestBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<BookDto> getAllBooks() {
        List<Book> books  = bookService.getAllBooks();
        return books.stream().map(bookService::mapBookToBookDto).collect(Collectors.toList());
    }

    @DeleteMapping ("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @PostMapping("/save")
    public void saveBook(HttpServletResponse response, BookDto bookDto) throws IOException {
        bookService.saveBook(bookDto);
        response.sendRedirect("/");
    }

}
