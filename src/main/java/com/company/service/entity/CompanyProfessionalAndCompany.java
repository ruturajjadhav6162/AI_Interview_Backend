package com.company.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
    }
)
public class CompanyProfessionalAndCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;

    @OneToOne
    private CompanyDetails companyDetails;

    public CompanyProfessionalAndCompany(String username, CompanyDetails company) {
        this.username = username;
        this.companyDetails = company;
    }
}
