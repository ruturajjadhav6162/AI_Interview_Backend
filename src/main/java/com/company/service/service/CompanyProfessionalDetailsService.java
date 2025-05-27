package com.company.service.service;

import com.company.service.dto.CompanyProfessionalCreationResponse;
import com.company.service.dto.CompanyProfessionalDetailsInput;

public interface CompanyProfessionalDetailsService {
    CompanyProfessionalCreationResponse professionalCreation(CompanyProfessionalDetailsInput companyProfessionalDetailsInput);
}
