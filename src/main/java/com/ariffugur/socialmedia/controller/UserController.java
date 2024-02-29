package com.ariffugur.socialmedia.controller;

import com.ariffugur.socialmedia.dto.AuthRequest;
import com.ariffugur.socialmedia.dto.CreateUserRequest;
import com.ariffugur.socialmedia.model.User;
import com.ariffugur.socialmedia.service.JwtService;
import com.ariffugur.socialmedia.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public User createUser(@RequestBody CreateUserRequest user) {
        return userService.registerUser(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        return userService.updateUser(user, reqUser.getId());
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable("id") Integer id) throws Exception {
        return userService.deleteUser(id);
    }

    @PutMapping("/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable("userId2") Integer userId2) throws Exception {
        return userService.followUser(jwt, userId2);
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam("query") String query) {
        return userService.searchUsers(query);
    }

    @GetMapping("/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {

        return userService.findUserByJwt(jwt);

    }
}
