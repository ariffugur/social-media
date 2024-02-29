package com.ariffugur.socialmedia.service;

import com.ariffugur.socialmedia.dto.CreateUserRequest;
import com.ariffugur.socialmedia.model.User;
import com.ariffugur.socialmedia.repository.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    public User registerUser(CreateUserRequest user) {
        User newUser = User.builder()
                .firstName(user.firstName())
                .lastName(user.lastName())
                .username(user.username())
                .password(bCryptPasswordEncoder.encode(user.password()))
                .role(user.role())
                .build();
        return userRepository.save(newUser);
    }

    public User findUserById(Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User not found with id: " + id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(null);
    }

    public User followUser(String jwt, Integer userId2) throws Exception {
        User reqUser = findUserByJwt(jwt);
        User user1 = findUserById(reqUser.getId());
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
        if (user.getUsername() != null) {
            oldUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            oldUser.setPassword(user.getPassword());
        }
        return save(oldUser);
    }

    public List<User> searchUsers(@RequestParam("query") String query) {
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

    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public User findUserByJwt(String jwt) {
        String username = jwtService.extractUsername(jwt.substring(7));
        return findUserByUsername(username);
    }
}
