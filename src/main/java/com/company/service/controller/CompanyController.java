package com.company.service.controller;

import com.company.service.dto.CompanyCreationResponse;
import com.company.service.dto.CompanyDetailsGet;
import com.company.service.dto.CompanyServiceInput;
import com.company.service.entity.CompanyProfessionalDetails;
import com.company.service.service.CompanyDetailsServiceImpl;
import com.company.service.service.CompanyProfessionalDetailsServiceImpl;
import com.company.service.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/company/")
public class CompanyController {
   @Autowired
   CompanyDetailsServiceImpl companyDetailsService;

   @GetMapping("allCompanyNames")
   public List<String> getAllCompanyNames() {
       return companyDetailsService.getAllCompanyName();
   }

   @PostMapping("createCompany")
    public CompanyCreationResponse createCompany(@RequestBody CompanyServiceInput companyServiceInput) {
       return companyDetailsService.createCompany(companyServiceInput);
   }

   @GetMapping("companyDetails/{id}")
    public CompanyDetailsGet getCompanyDetails(@PathVariable int id) {
       return companyDetailsService.getCompanyDetails(id);
   }

}
