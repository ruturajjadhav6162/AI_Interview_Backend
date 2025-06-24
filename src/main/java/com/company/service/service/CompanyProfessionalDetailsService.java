package com.company.service.service;

import com.company.service.dto.CompanyProfessionalCreationResponse;
import com.company.service.dto.CompanyProfessionalDetailsInput;
import com.company.service.entity.CompanyProfessionalAndCompany;
import com.company.service.entity.CompanyProfessionalDetails;

import java.util.List;

public interface CompanyProfessionalDetailsService {
    CompanyProfessionalCreationResponse professionalCreation(CompanyProfessionalDetailsInput companyProfessionalDetailsInput);
    CompanyProfessionalAndCompany getProfessionalDetails(String username);
//    CompanyProfessionalAndCompany professionalDetailsByUsername(String username);
    int getCompayIdByUsername(String username);

    List<CompanyProfessionalAndCompany> getAllProfessionalDetails();
}
