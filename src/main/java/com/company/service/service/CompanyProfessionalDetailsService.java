package com.company.service.service;

import com.company.service.dto.CompanyProfessionalCreationResponse;
import com.company.service.dto.CompanyProfessionalDetailsInput;
import com.company.service.entity.CompanyProfessionalDetails;

public interface CompanyProfessionalDetailsService {
    CompanyProfessionalCreationResponse professionalCreation(CompanyProfessionalDetailsInput companyProfessionalDetailsInput);
    CompanyProfessionalDetails getProfessionalDetails(int id);
    CompanyProfessionalDetails professionalDetailsByUsername(String username);
    int getCompayIdByUsername(String username);
}
