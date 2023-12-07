package kz.yeskebekov.metSocial.services.impl;

import kz.yeskebekov.metSocial.entities.Post;
import kz.yeskebekov.metSocial.entities.PostDTO;
import kz.yeskebekov.metSocial.mappers.UsersMapper;
import kz.yeskebekov.metSocial.repositories.PostRepository;
import kz.yeskebekov.metSocial.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<PostDTO> getAllPost() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTO = new ArrayList<>();
        for (Post post : posts){
            postDTO.add(new PostDTO(post.getId(), post.getPostText(), post.getPostDate(),
                    usersMapper.mapToUserDTO(post.getUsers())));
        }
        return postDTO;
    }

    @Override
    public List<PostDTO> getPostsByUser(Long userId) {
        List<PostDTO> postsDTO = new ArrayList<>();
        for(Post post : postRepository.findAll()){
            if(post.getUsers().getId()==userId){
                postsDTO.add(new PostDTO(post.getId(),post.getPostText(),
                        post.getPostDate(),usersMapper.mapToUserDTO(post.getUsers())));
            }
        }
        return postsDTO;
    }

    @Override
    public PostDTO addPost(Post post) {
        Post savesPost = postRepository.save(post);
        return new PostDTO(savesPost.getId(),savesPost.getPostText(),savesPost.getPostDate(),
                usersMapper.mapToUserDTO(savesPost.getUsers()));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostDTO updatePost(Post post) {
        Post updPost = postRepository.findAllById(post.getId());
        updPost.setPostText(post.getPostText());
        return new PostDTO(updPost.getId(), updPost.getPostText(),updPost.getPostDate(),
                usersMapper.mapToUserDTO(updPost.getUsers()));
    }
}
