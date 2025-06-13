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
   @Autowired
   CompanyProfessionalDetailsServiceImpl companyProfessionalDetailsService;
   @Autowired
   JWTUtil jwtUtil;

   @GetMapping("allCompanyNames")
   public List<String> getAllCompanyNames() {
       return companyDetailsService.getAllCompanyName();
   }

   @PostMapping("createCompany")
    public CompanyCreationResponse createCompany(@RequestBody CompanyServiceInput companyServiceInput) {
       return companyDetailsService.createCompany(companyServiceInput);
   }

   @GetMapping("companyDetails")
    public CompanyDetailsGet getCompanyDetails(@RequestParam int id) {
       return companyDetailsService.getCompanyDetails(id);
   }
   @GetMapping("token")
    public String getToken(HttpServletRequest request) {
       String authorization = request.getHeader("Authorization");
       String token = null;
       if (authorization != null && authorization.startsWith("Bearer ")) {
           token = authorization.substring(7);
           Claims body = jwtUtil.extractToken(token);
           int companyId=companyProfessionalDetailsService.getCompayIdByUsername(body.getSubject());
           return jwtUtil.generateToken(companyId,request);
       }
       throw new RuntimeException("Invalid Authorization");
   }

   @GetMapping("tokenDetails")
    public String getTokenDetails(HttpServletRequest request) {
       String authorization = request.getHeader("Authorization");
       String token = null;
       if (authorization != null && authorization.startsWith("Bearer ")) {
           token = authorization.substring(7);
           Claims body = jwtUtil.extractToken(token);
           return body.getSubject()+" "+body.get("companyid",Integer.class)+" "+body.get("role",String.class);
       }
       throw new RuntimeException("Error extracting details");
   }
}
