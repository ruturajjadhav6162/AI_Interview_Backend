package com.company.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyCreationResponse {
    String companyName;
    String companyDescription;
    String responseMessage;
}
