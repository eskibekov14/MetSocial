package kz.yeskebekov.metSocial.services;

import kz.yeskebekov.metSocial.entities.Post;
import kz.yeskebekov.metSocial.entities.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPost();
    List<PostDTO> getPostsByUser(Long userId);
    PostDTO addPost(Post post);
    void deletePost(Long id);
    PostDTO updatePost(Post post);
}
