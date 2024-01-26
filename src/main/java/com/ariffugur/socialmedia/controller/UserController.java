package com.ariffugur.socialmedia.controller;

import com.ariffugur.socialmedia.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User(1, "arif", "ugur", "arifugur@gmail.com", "123456");
        User user2 = new User(2, "idris", "elba", "idriselba@gmail.com", "123456");
        users.add(user1);
        users.add(user2);
        return users;
    }

    @GetMapping("/user/{id}")
    public User getByUserId(@PathVariable("id") Integer id) {
        User user1 = new User(1, "arif", "ugur", "arifugur@gmail.com", "123456");
        user1.setId(id);
        return user1;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        return newUser;

    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        User user1 = new User(1, "arif", "ugur", "arifugur@gmail.com", "123456");
        if (user.getFirstName() != null) {
            user1.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            user1.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            user1.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            user1.setPassword(user.getPassword());
        }
        return user1;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        return "user deleted " + id;

    }
}
