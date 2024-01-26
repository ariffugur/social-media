package com.ariffugur.socialmedia.controller;

import com.ariffugur.socialmedia.model.User;
import com.ariffugur.socialmedia.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getByUserId(@PathVariable("id") Integer id) throws Exception {
        return userService.findUserById(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody User user) throws Exception {
        return userService.followUser(id, user.getId());
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable("id") Integer id) throws Exception {
        return userService.deleteUser(id);
    }

    @PutMapping("/user/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable("userId1") Integer userId1, @PathVariable("userId2") Integer userId2) throws Exception {
        return userService.followUser(userId1, userId2);
    }

    @GetMapping("/users/search")
    public List<User> searchUsers(@RequestParam("query") String query) {
        return userService.searchUsers(query);
    }
}
