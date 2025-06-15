package com.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserCreationResponse {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
}
