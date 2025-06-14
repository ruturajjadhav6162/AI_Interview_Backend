package com.user.service.controller;

import com.user.service.dto.AuthRequest;
import com.user.service.entity.Users;
import com.user.service.repository.UserDetaiilsRepsitory;
import com.user.service.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    UserDetaiilsRepsitory userDetaiilsRepsitory;
    @PostMapping("/token")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            return jwtUtil.generateToken(authRequest.getUsername());
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get/{username}")
    public Optional<Users> getUserByUsername(@PathVariable String username) {
        return userDetaiilsRepsitory.findByUsername(username);
    }

}
