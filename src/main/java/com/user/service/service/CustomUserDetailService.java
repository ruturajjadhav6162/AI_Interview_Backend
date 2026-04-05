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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserCreationResponse createUser(UserCreationInput userCreationInput) {
        try {
            Users user = new Users(
                    userCreationInput.getUsername(),
                    passwordEncoder.encode(userCreationInput.getPassword()),
                    Role.USER,
                    userCreationInput.getEmail(),
                    userCreationInput.getPhone(),
                    userCreationInput.getFirstName(),
                    userCreationInput.getLastName()
            );
            userRepository.save(user);
            return new UserCreationResponse(
                    userCreationInput.getUsername(),
                    userCreationInput.getEmail(),
                    userCreationInput.getFirstName(),
                    userCreationInput.getLastName(),
                    userCreationInput.getPhone()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CompanyProfessionalCreationResponse createCompanyUser(CompanyProfessionalDetailsInput companyProfessionalDetailsInput) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CompanyProfessionalAndCompanyRegistration companyProfessional= new CompanyProfessionalAndCompanyRegistration(
                companyProfessionalDetailsInput.getCompanyName(),
                companyProfessionalDetailsInput.getUsername()
        );
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
            Users user  = new Users(
                    companyProfessionalDetailsInput.getUsername(),
                    passwordEncoder.encode(companyProfessionalDetailsInput.getPassword()),
                    Role.COMPANY,
                    companyProfessionalDetailsInput.getEmail(),
                    companyProfessionalDetailsInput.getPhone(),
                    companyProfessionalDetailsInput.getFirstName(),
                    companyProfessionalDetailsInput.getLastName()
            );
            userRepository.save(user);
            return new CompanyProfessionalCreationResponse(
                    companyProfessionalDetailsInput.getUsername(),
                    companyProfessionalDetailsInput.getFirstName() + " " + companyProfessionalDetailsInput.getLastName(),
                    companyProfessionalDetailsInput.getEmail(),
                    companyProfessionalDetailsInput.getCompanyName(),
                    companyProfessionalDetailsInput.getPhone()

            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
