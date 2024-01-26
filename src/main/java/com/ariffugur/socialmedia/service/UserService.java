package com.ariffugur.socialmedia.service;

import com.ariffugur.socialmedia.model.User;
import com.ariffugur.socialmedia.repository.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User not found with id: " + id);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        user2.getFollowers().add(user1.getId());
        user1.getFollowing().add(user2.getId());
        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    public User updateUser(User user, Integer id) throws Exception {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()) {
            throw new Exception("User not found with id: " + id);
        }
        User oldUser = user1.get();
        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            oldUser.setPassword(user.getPassword());
        }
        return userRepository.save(oldUser);
    }

    public List<User> searchUsers(@Param("query") String query) {
        return userRepository.searchUsers(query);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User deleteUser(Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception("User not found with id: " + id);
        }
        userRepository.delete(user.get());
        return user.get();
    }

}
