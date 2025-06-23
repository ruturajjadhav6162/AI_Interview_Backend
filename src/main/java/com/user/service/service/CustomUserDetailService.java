package com.user.service.service;

import com.user.service.dto.*;
import com.user.service.entity.Users;
import com.user.service.repository.UserDetaiilsRepsitory;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public CompanyProfessionalCreationResponse createCompanyUser(CompanyProfessionalDetailsInput companyProfessionalDetailsInput) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CompanyProfessionalAndCompanyRegistration companyProfessional=CompanyProfessionalAndCompanyRegistration.builder()
                .companyName(companyProfessionalDetailsInput.getCompanyName())
                .username(companyProfessionalDetailsInput.getUsername())
                .build();
        WebClient webClient = WebClient.builder().build();
        webClient.post()
                .uri("http://localhost:8002/companyProfessional/createProfessional")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .bodyValue(companyProfessional)
                .retrieve()
                .bodyToMono(CompanyProfessionalCreationResponse.class)
                .block();;
        try {
            Users user = Users.builder()
                    .username(companyProfessionalDetailsInput.getUsername())
                    .password(passwordEncoder.encode(companyProfessionalDetailsInput.getPassword()))
                    .email(companyProfessionalDetailsInput.getEmail())
                    .firstName(companyProfessionalDetailsInput.getFirstName())
                    .lastName(companyProfessionalDetailsInput.getLastName())
                    .phone(companyProfessionalDetailsInput.getPhone())
                    .role(Role.COMPANY)
                    .build();
            userRepository.save(user);
            return CompanyProfessionalCreationResponse.builder()
                    .username(companyProfessionalDetailsInput.getUsername())
                    .professionalName(companyProfessionalDetailsInput.getFirstName()+" "+companyProfessionalDetailsInput.getLastName())
                    .email(companyProfessionalDetailsInput.getEmail())
                    .phone(companyProfessionalDetailsInput.getPhone())
                    .professionalCompany(companyProfessionalDetailsInput.getCompanyName())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
