package com.company.service.controller;

import com.company.service.dto.CompanyCreationResponse;
import com.company.service.dto.CompanyDetailsGet;
import com.company.service.dto.CompanyServiceInput;
import com.company.service.service.CompanyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company/")
public class CompanyController {
   @Autowired
    CompanyDetailsService companyDetailsService;

   @GetMapping("allCompanyNames")
   public List<String> getAllCompanyNames() {
       return companyDetailsService.getAllCompanyName();
   }

   @PostMapping("createCompany")
    public CompanyCreationResponse createCompany(CompanyServiceInput companyServiceInput) {
       return companyDetailsService.createCompany(companyServiceInput);
   }

   @GetMapping("companyDetails")
    public CompanyDetailsGet getCompanyDetails(@RequestParam int id) {
       return companyDetailsService.getCompanyDetails(id);
   }
}
