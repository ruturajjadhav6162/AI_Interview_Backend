package com.company.service.repository;

import com.company.service.entity.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Integer> {
}
