package com.company.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfessionalCreationResponse {
    private String professionalName;
    private String username;
    private String emailId;
    private String professionalCompany;
    private long phoneNumber;
}


