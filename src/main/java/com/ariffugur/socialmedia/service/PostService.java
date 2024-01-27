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

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post createNewPost(Post post, Integer userId) throws Exception {
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setImage(post.getImage());
        newPost.setUser(userService.findUserById(userId));
        return postRepository.save(newPost);

    }

    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (post.getUser().getId() != user.getId()) {
            throw new Exception("Post not found with id: " + postId);
        }
        postRepository.delete(post);
        return "Post deleted successfully";
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

    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }
        userService.save(user);
        return post;
    }

    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
