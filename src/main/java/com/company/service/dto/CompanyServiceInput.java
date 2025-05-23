package com.company.service.dto;

import lombok.Data;

@Data
public class CompanyServiceInput {
    private String companyName;
    private String industry;
    private int  companySize;
    private String companyDescription;
    private String companyWebsite;
    private String companyLocation;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailID;
}
