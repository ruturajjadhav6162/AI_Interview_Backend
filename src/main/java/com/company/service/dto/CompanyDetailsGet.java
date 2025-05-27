package com.company.service.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompanyDetailsGet {

    private String companyName;
    private String industry;
    private int  companySize;
    private String companyDescription;
    private String companyWebsite;
    private String companyLocation;
}
