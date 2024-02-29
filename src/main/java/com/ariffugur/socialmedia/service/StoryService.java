package com.ariffugur.socialmedia.service;

import com.ariffugur.socialmedia.dto.CreateStoryRequest;
import com.ariffugur.socialmedia.model.Story;
import com.ariffugur.socialmedia.model.User;
import com.ariffugur.socialmedia.repository.StoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StoryService {
    private final StoryRepository storyRepository;
    private final UserService userService;

    public StoryService(StoryRepository storyRepository, UserService userService) {
        this.storyRepository = storyRepository;
        this.userService = userService;
    }

    public Story createStory(CreateStoryRequest request, String jwt) {
        User user = userService.findUserByJwt(jwt);
        Story story = Story.builder()
                .image(request.image())
                .caption(request.caption())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
        return storyRepository.save(story);
    }
}
