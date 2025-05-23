package com.company.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyResponse {
    String companyName;
    String companyDescription;
    String responseMessage;
}
