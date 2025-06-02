package com.company.service.service;

import com.company.service.dto.CompanyProfessionalCreationResponse;
import com.company.service.dto.CompanyProfessionalDetailsInput;
import com.company.service.entity.CompanyProfessionalDetails;
import com.company.service.repository.CompanyDetailsRepository;
import com.company.service.repository.CompanyProfessionalDetailsRepostory;
import org.springframework.stereotype.Service;

@Service
public class CompanyProfessionalDetailsServiceImpl implements CompanyProfessionalDetailsService {

//    private CompanyDetailsRepository companyDetailsRepository;
    private CompanyDetailsServiceImpl companyDetailsService;
    private CompanyProfessionalDetailsRepostory companyProfessionalDetailsRepostory;

    @Override
    public CompanyProfessionalCreationResponse professionalCreation(CompanyProfessionalDetailsInput companyProfessionalDetailsInput) throws RuntimeException{
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

    @Override
    public CompanyProfessionalDetails getProfessionalDetails(int id) throws RuntimeException{
        CompanyProfessionalDetails professionalInfo=companyProfessionalDetailsRepostory.findById(id).orElse(null);
        if(professionalInfo!=null){
            return professionalInfo;
        }
        else{
            throw new RuntimeException("Professional details not found");
        }
    }


}
