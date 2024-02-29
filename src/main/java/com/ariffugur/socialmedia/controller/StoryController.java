package com.ariffugur.socialmedia.controller;

import com.ariffugur.socialmedia.dto.CreateStoryRequest;
import com.ariffugur.socialmedia.model.Story;
import com.ariffugur.socialmedia.service.StoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/story")
public class StoryController {
    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping("/create")
    public Story createStory(@RequestBody CreateStoryRequest request, @RequestHeader("Authorization") String jwt) {
        return storyService.createStory(request, jwt);

    }
}
