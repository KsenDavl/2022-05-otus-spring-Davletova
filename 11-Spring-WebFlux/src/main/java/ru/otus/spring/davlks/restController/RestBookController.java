package ru.otus.spring.davlks.restController;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.otus.spring.davlks.dao.BookDao;
import ru.otus.spring.davlks.entity.Book;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/book")
public class RestBookController {

    private final BookDao bookDao;

    public RestBookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/all")
    public Flux<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @DeleteMapping ("/delete/{id}")
    public void deleteBook(@PathVariable String id) {
        bookDao.deleteById(id).block();
    }

    @PostMapping("/save")
    public void saveBook(HttpServletResponse response, Book book) throws IOException {
        bookDao.save(book).block();
        response.sendRedirect("/");
    }

}
