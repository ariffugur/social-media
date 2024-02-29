package com.ariffugur.socialmedia.controller;

import com.ariffugur.socialmedia.dto.CreateReelRequest;
import com.ariffugur.socialmedia.model.Reel;
import com.ariffugur.socialmedia.service.ReelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reels")
public class ReelController {
    private final ReelService reelService;

    public ReelController(ReelService reelService) {
        this.reelService = reelService;
    }

    @PostMapping("/create")
    public Reel createReel(@RequestBody CreateReelRequest request, @RequestHeader("Authorization") String jwt) {
        return reelService.createReel(request, jwt);
    }

    @GetMapping("/all")
    public List<Reel> findAllReels() {
        return reelService.findAllReels();
    }

    @GetMapping("/findbyuserid")
    public List<Reel> findByUserId(@RequestHeader("Authorization") String jwt) {
        return reelService.findByUserId(jwt);
    }
}
