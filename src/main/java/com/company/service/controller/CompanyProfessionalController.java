package com.company.service.controller;

import com.company.service.dto.CompanyProfessionalCreationResponse;
import com.company.service.dto.CompanyProfessionalDetailsInput;
import com.company.service.entity.CompanyProfessionalDetails;
import com.company.service.service.CompanyProfessionalDetailsService;
import com.company.service.service.CompanyProfessionalDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companyProfessional/")
public class CompanyProfessionalController {

    @Autowired
    CompanyProfessionalDetailsServiceImpl companyProfessionalDetailsService;

    @GetMapping("details/{id}")
    public CompanyProfessionalDetails getProfessionalDetails(@PathVariable int id){
        return companyProfessionalDetailsService.getProfessionalDetails(id);
    }

    @PostMapping("createProfessional")
    public CompanyProfessionalCreationResponse professionalCreation(CompanyProfessionalDetailsInput companyProfessionalDetailsInput){
        return companyProfessionalDetailsService.professionalCreation(companyProfessionalDetailsInput);
    }
}
