package com.ariffugur.socialmedia.repository;

import com.ariffugur.socialmedia.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    public List<Story> findByUserId(Integer id);
}
