package com.user.service.service;

import com.user.service.dto.Role;
import com.user.service.dto.UserCreationInput;
import com.user.service.dto.UserCreationResponse;
import com.user.service.entity.Users;
import com.user.service.repository.UserDetaiilsRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserDetaiilsRepsitory userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found "));
    }

    public UserCreationResponse createUser(UserCreationInput userCreationInput) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            Users user = Users.builder()
                    .username(userCreationInput.getUsername())
                    .password(passwordEncoder.encode(userCreationInput.getPassword()))
                    .email(userCreationInput.getEmail())
                    .firstName(userCreationInput.getFirstName())
                    .lastName(userCreationInput.getLastName())
                    .phone(userCreationInput.getPhone())
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            return UserCreationResponse.builder()
                    .username(userCreationInput.getUsername())
                    .email(userCreationInput.getEmail())
                    .firstName(userCreationInput.getFirstName())
                    .lastName(userCreationInput.getLastName())
                    .phone(userCreationInput.getPhone())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
