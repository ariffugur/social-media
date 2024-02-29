package com.ariffugur.socialmedia.controller;

import com.ariffugur.socialmedia.dto.CreateCommentRequest;
import com.ariffugur.socialmedia.model.Comment;
import com.ariffugur.socialmedia.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/post/{postId}")
    public Comment createComment(@RequestBody CreateCommentRequest comment, @RequestHeader("Authorization") String jwt, @PathVariable("postId") Integer postId) throws Exception {
        return commentService.createComment(comment, jwt, postId);

    }

    @PutMapping("/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable("commentId") Integer commentId) throws Exception {
        return commentService.likeComment(jwt, commentId);

    }
}
