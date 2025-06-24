package com.company.service.repository;

import com.company.service.entity.CompanyProfessionalAndCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyProfessionalAndCompanyRepository extends JpaRepository<CompanyProfessionalAndCompany, Integer> {
    Optional<CompanyProfessionalAndCompany> findCompanyProfessionalAndCompanyByUsername(String username);

    Optional<Object> findByUsername(String username);
}
