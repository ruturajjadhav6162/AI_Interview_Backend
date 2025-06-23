package com.user.service.dto;

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
    private String username;
    private String password;
    private  String firstName;
    private  String lastName;
    private String email;
    private long phone;

}
