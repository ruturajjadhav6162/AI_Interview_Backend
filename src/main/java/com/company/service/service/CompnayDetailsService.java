package com.company.service.service;

import com.company.service.dto.CompanyResponse;
import com.company.service.dto.CompanyServiceInput;
import com.company.service.entity.CompanyDetails;
import com.company.service.repository.CompanyDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class CompnayDetailsService implements CompanyDetailsServiceImpl{
    private CompanyDetailsRepository companyDetailsRepository;
    private CompanyDetails companyDetails;
    public CompanyResponse createCompany(CompanyServiceInput companyServiceInput) {
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
            throw new RuntimeException("Error while saving company information");
        }
        return new CompanyResponse(companyServiceInput.getCompanyName(),companyServiceInput.getCompanyDescription(),"Company Created Successfully");

    }
}
