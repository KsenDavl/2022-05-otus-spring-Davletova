package ru.otus.spring.davlks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.davlks.entity.Comment;
import ru.otus.spring.davlks.service.CommentService;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public String getAllComments(@RequestParam Long bookId, ModelMap model) {
        List<Comment> comments  = commentService.getAllBookComments(bookId);
        model.addAttribute("comments", comments);
        return "comment-list";
    }

    @PostMapping("/add")
    public String addComment(@ModelAttribute("comment") Comment comment) {
        commentService.addComment(comment);
        return "redirect:/comment/all";
    }

    @GetMapping("/delete")
    public String deleteComment(@RequestParam Long id) {
       commentService.deleteCommentById(id);
        return "redirect:/comment/all";
    }
}
