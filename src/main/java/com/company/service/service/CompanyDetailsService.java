package com.company.service.service;

import com.company.service.dto.CompanyCreationResponse;
import com.company.service.dto.CompanyDetailsGet;
import com.company.service.dto.CompanyServiceInput;
import com.company.service.entity.CompanyDetails;

import java.util.List;

public interface CompanyDetailsService {
    CompanyCreationResponse createCompany(CompanyServiceInput companyServiceInput);
    CompanyDetailsGet getCompanyDetails(int companyId);
    List<String> getAllCompanyName();
    CompanyDetails companyDetailsByName(String companyName);

}
