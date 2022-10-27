package ru.otus.spring.davlks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;
import ru.otus.spring.davlks.dao.BookDao;
import ru.otus.spring.davlks.entity.Book;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentPageController {

    private final BookDao bookDao;

    public CommentPageController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/all/{bookId}")
    public String getAllComments(@PathVariable String bookId, ModelMap model) {
        Mono<Book> book = bookDao.findById(bookId);
        List<String> comments = book.block().getComments();
        model.addAttribute("comments", comments == null ? new ArrayList<>() : comments);
        return "comment-list";
    }
}
