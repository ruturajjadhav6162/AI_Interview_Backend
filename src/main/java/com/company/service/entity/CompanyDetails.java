package com.company.service.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "company_details")
public class CompanyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyDetailsId;

    private String companyName;
    private String industry;
    private int  companySize;
    private String companyDescription;
    private String companyWebsite;
    private String companyLocation;


}
