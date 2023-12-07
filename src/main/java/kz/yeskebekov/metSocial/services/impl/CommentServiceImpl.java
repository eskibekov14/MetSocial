package kz.yeskebekov.metSocial.services.impl;

import kz.yeskebekov.metSocial.entities.Comment;
import kz.yeskebekov.metSocial.entities.CommentDto;
import kz.yeskebekov.metSocial.entities.PostDTO;
import kz.yeskebekov.metSocial.mappers.UsersMapper;
import kz.yeskebekov.metSocial.repositories.CommentRepository;
import kz.yeskebekov.metSocial.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public List<CommentDto> getComments(Long postId) {
        List<Comment> allComments = commentRepository.findAll();
        List<CommentDto> postComments = new ArrayList<>();
        for (Comment comment : allComments){
            if(comment.getPost().getId() == postId){
                postComments.add(new CommentDto(comment.getId(), comment.getCommentText(),
                        comment.getCommentDate(),usersMapper.mapToUserDTO(comment.getUsers()),
                        new PostDTO(comment.getPost().getId(), comment.getPost().getPostText(),
                                comment.getPost().getPostDate(),
                                usersMapper.mapToUserDTO(comment.getPost().getUsers()))));
            }
        }
        return postComments;
    }

    @Override
    public CommentDto updateComment(Comment comment) {
        Comment updatedComment = commentRepository.findAllById(comment.getId());
        Comment savedComment = new Comment();
        if(updatedComment!=null) {
            updatedComment.setCommentDate(comment.getCommentDate());
            updatedComment.setCommentText(comment.getCommentText());
            savedComment = commentRepository.save(updatedComment);
        }
        return new CommentDto(savedComment.getId(), savedComment.getCommentText(),
                savedComment.getCommentDate() ,
                usersMapper.mapToUserDTO(savedComment.getUsers()),
                new PostDTO(savedComment.getPost().getId(), savedComment.getPost().getPostText(),
                        savedComment.getPost().getPostDate(),
                        usersMapper.mapToUserDTO(savedComment.getPost().getUsers()))
                );
    }

    @Override
    public CommentDto addComment(Comment comment) {
        Comment newComment = commentRepository.save(comment);
        return new CommentDto(newComment.getId(), newComment.getCommentText(),
                newComment.getCommentDate(), usersMapper.mapToUserDTO(newComment.getUsers()),
                new PostDTO(newComment.getPost().getId(), newComment.getPost().getPostText(),
                        newComment.getPost().getPostDate(),
                        usersMapper.mapToUserDTO(newComment.getPost().getUsers())));
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
