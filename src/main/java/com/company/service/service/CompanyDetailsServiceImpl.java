package com.company.service.service;

import com.company.service.dto.CompanyResponse;
import com.company.service.dto.CompanyServiceInput;
import com.company.service.entity.CompanyDetails;

public interface CompanyDetailsServiceImpl {
    CompanyResponse createCompany(CompanyServiceInput companyServiceInput);

}
