package com.ariffugur.socialmedia.service;

import com.ariffugur.socialmedia.dto.CreateCommentRequest;
import com.ariffugur.socialmedia.model.Comment;
import com.ariffugur.socialmedia.model.Post;
import com.ariffugur.socialmedia.model.User;
import com.ariffugur.socialmedia.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    private final PostService postService;
    private final UserService userService;
    private final JwtService jwtService;
    private final CommentRepository commentRepository;

    public CommentService(PostService postService, UserService userService, JwtService jwtService, CommentRepository commentRepository) {
        this.postService = postService;
        this.userService = userService;
        this.jwtService = jwtService;
        this.commentRepository = commentRepository;
    }

    public Comment createComment(CreateCommentRequest comment, String jwt, Integer postId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.findPostById(postId);
        Comment newComment = new Comment();
        newComment.setContent(comment.content());
        newComment.setUser(userService.findUserById(reqUser.getId()));
        newComment.setCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(newComment);
        post.getComments().add(savedComment);
        postService.savePost(post);
        return savedComment;

    }

    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            throw new Exception("Comment not found with id: " + commentId);
        }
        return comment.get();
    }

    public Comment likeComment(String jwt, Integer commentId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Comment comment = findCommentById(commentId);
        if (!comment.getLiked().contains(reqUser)) {
            comment.getLiked().add(reqUser);

        } else {
            comment.getLiked().remove(reqUser);
        }
        return commentRepository.save(comment);
    }

}

