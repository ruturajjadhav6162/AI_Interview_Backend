package com.company.service.controller;

import com.company.service.dto.CompanyProfessionalCreationResponse;
import com.company.service.dto.CompanyProfessionalDetailsInput;
import com.company.service.entity.CompanyProfessionalAndCompany;
import com.company.service.entity.CompanyProfessionalDetails;
import com.company.service.service.CompanyProfessionalDetailsService;
import com.company.service.service.CompanyProfessionalDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companyProfessional/")
public class CompanyProfessionalController {

    @Autowired
    CompanyProfessionalDetailsServiceImpl companyProfessionalDetailsService;

    @GetMapping("details/{username}")
    public CompanyProfessionalAndCompany getProfessionalDetails(@PathVariable String username) {
        return companyProfessionalDetailsService.getProfessionalDetails(username);
    }

    @PostMapping("createProfessional")
    public CompanyProfessionalCreationResponse professionalCreation(@RequestBody CompanyProfessionalDetailsInput companyProfessionalDetailsInput){
        return companyProfessionalDetailsService.professionalCreation(companyProfessionalDetailsInput);
    }
    @GetMapping("getAllProfessional")
    public List<CompanyProfessionalAndCompany> getAllProfessional(){
        return companyProfessionalDetailsService.getAllProfessionalDetails();
    }
}
