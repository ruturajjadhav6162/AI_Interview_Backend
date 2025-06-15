package com.company.service.controller;

import com.company.service.service.CompanyProfessionalDetailsServiceImpl;
import com.company.service.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt/")
public class AuthController {
    @Autowired
    CompanyProfessionalDetailsServiceImpl companyProfessionalDetailsService;
    @Autowired
    JWTUtil jwtUtil;
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
            return body.get("tokenFrom",String.class)+" "+body.getSubject()+" "+body.get("companyId",Integer.class)+" "+body.get("role",String.class);
        }
        throw new RuntimeException("Error extracting details");
    }
}
