package com.company.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfessionalDetailsInput {
    private String companyName;
    private  String firstName;
    private  String lastName;
    private String emailId;
    private float phoneNumber;

}
