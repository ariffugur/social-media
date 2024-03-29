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

    @PostMapping("/create")
    public ResponseEntity<Post> createNewPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post)  throws Exception {
        Post createdPost = postService.createNewPost(jwt, post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId,@RequestHeader("Authorization") String jwt) throws Exception {
        String message = postService.deletePost(postId, jwt);
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

    @PutMapping("/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        Post post = postService.savedPost(postId, jwt);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likedPost(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        Post post = postService.likePost(postId, jwt);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
