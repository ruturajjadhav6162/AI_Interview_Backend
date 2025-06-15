package com.job.service.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsFromToken{
    private String username;
    private String role;
    private Integer companyId;
    private String tokenFrom;
}

