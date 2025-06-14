package com.job.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsFromToken{
    private String username;
    private String role;
    private int companyId;
    private String tokenFrom;
}

