package com.company.service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name="Company_Professional_Details")
@Getter
@Setter
public class CompanyProfessionalDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int companyProfessionalId;

    private  String firstName;
    private  String lastName;
    private String emailId;
    private float phoneNumber;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyDetails company;

    public CompanyProfessionalDetails(String firstName,String lastName,String emailId,float phoneNumber,CompanyDetails company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.company=company;
    }

    public CompanyProfessionalDetails() {

    }
}

