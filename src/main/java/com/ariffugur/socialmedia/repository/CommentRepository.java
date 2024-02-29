package com.ariffugur.socialmedia.repository;

import com.ariffugur.socialmedia.model.Comment;
import com.ariffugur.socialmedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
