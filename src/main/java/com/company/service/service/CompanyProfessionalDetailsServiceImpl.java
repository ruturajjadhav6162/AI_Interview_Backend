package com.company.service.service;

import com.company.service.dto.CompanyProfessionalCreationResponse;
import com.company.service.dto.CompanyProfessionalDetailsInput;
import com.company.service.entity.CompanyDetails;
import com.company.service.entity.CompanyProfessionalAndCompany;
import com.company.service.repository.CompanyProfessionalAndCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyProfessionalDetailsServiceImpl implements CompanyProfessionalDetailsService {

//    private CompanyDetailsRepository companyDetailsRepository;
    @Autowired
    private CompanyDetailsServiceImpl companyDetailsService;
    @Autowired
    private CompanyProfessionalAndCompanyRepository companyProfessionalAndCompanyRepository;
    private CompanyDetails companyDetails;

    @Override
    public CompanyProfessionalCreationResponse professionalCreation(CompanyProfessionalDetailsInput companyProfessionalDetailsInput) throws RuntimeException{
        try{
            CompanyDetails company=companyDetailsService.companyDetailsByName(companyProfessionalDetailsInput.getCompanyName());
            CompanyProfessionalAndCompany companyProfessionalDetails=new CompanyProfessionalAndCompany(companyProfessionalDetailsInput.getUsername(),company);
            companyProfessionalAndCompanyRepository.save(companyProfessionalDetails);
        }
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("Error saving company professional details");
        }
        return new CompanyProfessionalCreationResponse(companyProfessionalDetailsInput.getUsername(), companyProfessionalDetailsInput.getCompanyName());
    }

    @Override
    public CompanyProfessionalAndCompany getProfessionalDetails(String username) throws RuntimeException{
        CompanyProfessionalAndCompany professionalInfo= (CompanyProfessionalAndCompany) companyProfessionalAndCompanyRepository.findByUsername(username).orElse(null);
        if(professionalInfo!=null){
            return professionalInfo;
        }
        else{
            throw new RuntimeException("Professional details not found");
        }
    }

//    @Override
//    public CompanyProfessionalAndCompany professionalDetailsByUsername(String username) {
//        return companyProfessionalAndCompanyRepository.findCompanyProfessionalAndCompanyByUsername(username).orElse(null);
//    }

    @Override
    public int getCompayIdByUsername(String username) {
        return getProfessionalDetails(username).getCompanyDetails().getCompanyDetailsId();
    }

    @Override
    public List<CompanyProfessionalAndCompany> getAllProfessionalDetails(){
        return companyProfessionalAndCompanyRepository.findAll();
    }


}
