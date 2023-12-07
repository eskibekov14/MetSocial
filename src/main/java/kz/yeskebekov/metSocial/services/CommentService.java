package kz.yeskebekov.metSocial.services;

import kz.yeskebekov.metSocial.entities.Comment;
import kz.yeskebekov.metSocial.entities.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {
    List<CommentDto> getComments(Long postId);
    CommentDto updateComment(Comment comment);
    CommentDto addComment(Comment comment);
    void deleteComment(Long commentId);

}
