package com.user.service.controller;

import com.user.service.dto.*;
import com.user.service.entity.Users;
import com.user.service.repository.UserDetaiilsRepsitory;
import com.user.service.service.CustomUserDetailService;
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
    CustomUserDetailService customUserDetailService;
    @Autowired
    UserDetaiilsRepsitory userDetaiilsRepsitory;
    @PostMapping("/token")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @GetMapping("/get/{username}")
    public Users getUserByUsername(@PathVariable String username) {
        return userDetaiilsRepsitory.findByUsername(username);
    }

    @PostMapping("/createUser")
    public UserCreationResponse createUser(@RequestBody UserCreationInput userCreationInput) {
        return customUserDetailService.createUser(userCreationInput);
    }

    @PostMapping("/createProfessional")
    public CompanyProfessionalCreationResponse createProfessional(@RequestBody CompanyProfessionalDetailsInput companyProfessionalDetailsInput) {
        return  customUserDetailService.createCompanyUser(companyProfessionalDetailsInput);
    }

    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() {
        return userDetaiilsRepsitory.findAllByRole(Role.USER);
    }

    @GetMapping("/getAll")
    public List<Users> getAll() {
        return userDetaiilsRepsitory.findAll();
    }

}
