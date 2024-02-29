package com.ariffugur.socialmedia.service;

import com.ariffugur.socialmedia.model.Post;
import com.ariffugur.socialmedia.model.User;
import com.ariffugur.socialmedia.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final JwtService jwtService;

    public PostService(PostRepository postRepository, UserService userService, JwtService jwtService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public Post createNewPost(String jwt, Post post) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setImage(post.getImage());
        newPost.setUser(userService.findUserById(reqUser.getId()));
        return postRepository.save(newPost);

    }

    public String deletePost(Integer postId, String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = findPostById(postId);
        User user = userService.findUserById(reqUser.getId());
        if (post.getUser().getId().equals(user.getId())) {
            postRepository.delete(post);
            return "Post deleted successfully";
        } else {
            throw new Exception("Post not found with id: " + postId);
        }

    }

    public List<Post> findPostByUserId(Integer userId) throws Exception {
        return postRepository.findPostByUserId(userId);
    }

    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new Exception("Post not found with id: " + postId);
        }
        return post.get();
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Post savedPost(Integer postId, String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = findPostById(postId);
        User user = userService.findUserById(reqUser.getId());
        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }
        userService.save(user);
        return post;
    }

    public Post likePost(Integer postId, String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = findPostById(postId);
        User user = userService.findUserById(reqUser.getId());
        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
