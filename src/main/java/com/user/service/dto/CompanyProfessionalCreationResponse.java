package com.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CompanyProfessionalCreationResponse {
    private String professionalName;
    private String username;
    private String email;
    private String professionalCompany;
    private long phone;
}


