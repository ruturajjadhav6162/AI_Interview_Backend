package com.company.service.service;

import com.company.service.dto.CompanyProfessionalCreationResponse;
import com.company.service.dto.CompanyProfessionalDetailsInput;
import com.company.service.entity.CompanyProfessionalDetails;
import com.company.service.repository.CompanyDetailsRepository;
import com.company.service.repository.CompanyProfessionalDetailsRepostory;

public class CompanyProfessionalDetailsServiceImpl implements CompanyProfessionalDetailsService {

//    private CompanyDetailsRepository companyDetailsRepository;
    private CompanyDetailsServiceImpl companyDetailsService;
    private CompanyProfessionalDetailsRepostory companyProfessionalDetailsRepostory;

    @Override
    public CompanyProfessionalCreationResponse professionalCreation(CompanyProfessionalDetailsInput companyProfessionalDetailsInput) {
        try{
            int companyId=companyDetailsService.companyDetailsByName(companyProfessionalDetailsInput.getCompanyName());
            CompanyProfessionalDetails companyProfessionalDetails=new CompanyProfessionalDetails(companyProfessionalDetailsInput.getFirstName(), companyProfessionalDetailsInput.getLastName(), companyProfessionalDetailsInput.getEmailId(), companyProfessionalDetailsInput.getPhoneNumber(),companyId);
            companyProfessionalDetailsRepostory.save(companyProfessionalDetails);
        }
        catch(Exception e){
            throw new RuntimeException("Error saving company professional details");
        }
        return new CompanyProfessionalCreationResponse(companyProfessionalDetailsInput.getFirstName()+" "+companyProfessionalDetailsInput.getLastName(),companyProfessionalDetailsInput.getEmailId(),companyProfessionalDetailsInput.getCompanyName(),companyProfessionalDetailsInput.getPhoneNumber());
    }
}
