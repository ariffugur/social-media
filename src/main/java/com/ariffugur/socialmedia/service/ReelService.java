package com.ariffugur.socialmedia.service;

import com.ariffugur.socialmedia.dto.CreateReelRequest;
import com.ariffugur.socialmedia.model.Reel;
import com.ariffugur.socialmedia.model.User;
import com.ariffugur.socialmedia.repository.ReelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelService {
    private final ReelRepository reelRepository;
    private final UserService userService;

    public ReelService(ReelRepository reelRepository, UserService userService) {
        this.reelRepository = reelRepository;
        this.userService = userService;
    }

    public Reel createReel(CreateReelRequest request, String jwt) {
        User user = userService.findUserByJwt(jwt);
        Reel reel = new Reel();
        reel.setTitle(request.title());
        reel.setVideo(request.video());
        reel.setUser(user);
        return reelRepository.save(reel);

    }

    public List<Reel> findAllReels() {
        return reelRepository.findAll();
    }

    public List<Reel> findByUserId(String jwt) {
        User user = userService.findUserByJwt(jwt);
        return reelRepository.findByUserId(user.getId());
    }
}
