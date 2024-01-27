package com.ariffugur.socialmedia.controller;

import com.ariffugur.socialmedia.model.Post;
import com.ariffugur.socialmedia.response.ApiResponse;
import com.ariffugur.socialmedia.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create/user/{userId}")
    public ResponseEntity<Post> createNewPost(@RequestBody Post post, @PathVariable("userId") Integer userId) throws Exception {
        Post createdPost = postService.createNewPost(post, userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/post/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(Integer postId, Integer userId) throws Exception {
        String message = postService.deletePost(postId, userId);
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable("postId") Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/post/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) throws Exception {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() throws Exception {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.ACCEPTED);
    }

    @PutMapping("/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) throws Exception {
        Post post = postService.savedPost(postId, userId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
    @PutMapping("/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likedPost(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) throws Exception {
        Post post = postService.likePost(postId, userId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
