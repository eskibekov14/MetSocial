package kz.yeskebekov.metSocial.controllers;

import kz.yeskebekov.metSocial.entities.Comment;
import kz.yeskebekov.metSocial.entities.CommentDto;
import kz.yeskebekov.metSocial.services.CommentService;
import kz.yeskebekov.metSocial.services.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
public class CommentController {
    @Autowired
    private CommentService commentService;


    @GetMapping(value = "/{postId}")
    public List<CommentDto> getAllPostComment(@PathVariable("postId") Long postId){
        return commentService.getComments(postId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public CommentDto addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public CommentDto editComment(@RequestBody Comment comment){
        return commentService.updateComment(comment);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
    }
}
