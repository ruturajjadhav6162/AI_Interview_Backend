package com.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreationInput {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
}
