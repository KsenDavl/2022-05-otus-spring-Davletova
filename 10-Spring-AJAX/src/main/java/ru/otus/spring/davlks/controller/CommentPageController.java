package ru.otus.spring.davlks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.davlks.entity.Comment;
import ru.otus.spring.davlks.service.CommentService;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentPageController {

    private final CommentService commentService;

    public CommentPageController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all/{bookId}")
    public String getAllComments(@PathVariable Long bookId, ModelMap model) {
        List<Comment> comments  = commentService.getAllBookComments(bookId);
        model.addAttribute("comments", comments);
        return "comment-list";
    }
}
