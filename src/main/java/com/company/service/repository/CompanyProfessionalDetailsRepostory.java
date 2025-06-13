package com.company.service.repository;

import com.company.service.entity.CompanyProfessionalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CompanyProfessionalDetailsRepostory extends JpaRepository<CompanyProfessionalDetails, Integer> {
    Optional<CompanyProfessionalDetails> findByUsername(String username);
}
