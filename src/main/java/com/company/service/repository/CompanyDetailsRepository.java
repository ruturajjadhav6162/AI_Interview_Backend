package com.company.service.repository;

import com.company.service.entity.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Integer> {
    CompanyDetails getByCompanyDetailsId(int companyDetailsId);

    CompanyDetails getByCompanyName(String companyName);
}
