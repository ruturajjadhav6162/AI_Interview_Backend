package com.company.service.service;

import com.company.service.dto.CompanyCreationResponse;
import com.company.service.dto.CompanyServiceInput;

public interface CompanyDetailsServiceImpl {
    CompanyCreationResponse createCompany(CompanyServiceInput companyServiceInput);

}
