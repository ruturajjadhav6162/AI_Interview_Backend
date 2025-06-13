package com.company.service.service;

import com.company.service.dto.CompanyCreationResponse;
import com.company.service.dto.CompanyDetailsGet;
import com.company.service.dto.CompanyServiceInput;
import com.company.service.entity.CompanyDetails;
import com.company.service.repository.CompanyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {
    @Autowired
    private CompanyDetailsRepository companyDetailsRepository;
    private CompanyDetails companyDetails;

    // Enters Company Data in db when registering a company
    @Override
    public CompanyCreationResponse createCompany(CompanyServiceInput companyServiceInput) {
        companyDetails= CompanyDetails.builder()
                .companyName(companyServiceInput.getCompanyName())
                .industry(companyServiceInput.getIndustry())
                .companySize(companyServiceInput.getCompanySize())
                .companyDescription(companyServiceInput.getCompanyDescription())
                .companyWebsite(companyServiceInput.getCompanyWebsite())
                .companyLocation(companyServiceInput.getCompanyLocation())
                .build();
        try{companyDetailsRepository.save(companyDetails);}
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("Error while saving company information");
        }
        return new CompanyCreationResponse(companyServiceInput.getCompanyName(),companyServiceInput.getCompanyDescription(),"Company Created Successfully");
    }

    // Get Company Data to Display in Company Profile
    @Override
    public List<String> getAllCompanyName(){
        List<String> companyName= new ArrayList<>();
        for(CompanyDetails companyDetails: companyDetailsRepository.findAll()){
            companyName.add(companyDetails.getCompanyName());
        }
        return companyName;
    }

    // Get Company Details by companyId
    @Override
    public CompanyDetailsGet getCompanyDetails(Integer companyId){
        companyDetails= companyDetailsRepository.getByCompanyDetailsId(companyId);
        return new CompanyDetailsGet(companyDetails.getCompanyName(),
                companyDetails.getIndustry(),
                companyDetails.getCompanySize(),
                companyDetails.getCompanyDescription(),
                companyDetails.getCompanyWebsite(),
                companyDetails.getCompanyLocation());
    }

    @Override
    public CompanyDetails companyDetailsByName(String companyName){
        companyDetails=companyDetailsRepository.getByCompanyName(companyName);
        return companyDetails;
    }
}
