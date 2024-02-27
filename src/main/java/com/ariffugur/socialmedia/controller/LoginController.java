package com.ariffugur.socialmedia.controller;

import com.ariffugur.socialmedia.dto.AuthRequest;
import com.ariffugur.socialmedia.service.JwtService;
import com.ariffugur.socialmedia.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.username());
        }
        throw new UsernameNotFoundException("Invalid username or password");
    }
}
