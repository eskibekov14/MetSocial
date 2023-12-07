package kz.yeskebekov.metSocial.controllers;

import kz.yeskebekov.metSocial.entities.Post;
import kz.yeskebekov.metSocial.entities.PostDTO;
import kz.yeskebekov.metSocial.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDTO> getAllPost(){
        return postService.getAllPost();
    }

    @GetMapping(value = "/{userId}")
    public List<PostDTO> getPostsByUserId(@PathVariable(name = "userId") Long userId){
        return postService.getPostsByUser(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public PostDTO addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public PostDTO updatePost(@RequestBody Post post){
        return postService.updatePost(post);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/{postId}")
    public void deletePost(@PathVariable(name = "postId") Long postId){
        postService.deletePost(postId);
    }
}
