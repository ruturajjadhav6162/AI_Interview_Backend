package com.company.service.utils;

import com.company.service.entity.CompanyProfessionalDetails;
import com.company.service.repository.CompanyProfessionalDetailsRepostory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Component
public class JWTUtil {
    private final String Secret="my-super-secret-key-that-is-long-enough-1234567898!@#";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Secret.getBytes());
    @Autowired
    CompanyProfessionalDetailsRepostory companyProfessionalDetailsRepostory;
    public Claims extractToken(String token) {
//
//        Date now = new Date();
//        long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
//        Date expiry = new Date(now.getTime() + EXPIRATION_TIME);
        String[] tokens = token.split(" ");
        Claims body = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(tokens[1])
                .getPayload();
        return body;
    }
    public boolean validateToken(String token) {
        return !expiration(token);
    }

    private boolean expiration(String token) {
//        String[] tokens = token.split(" ");
        Claims body = extractToken(token);
        return body.getExpiration().before(new Date());
    }

    public String generateToken( int companyid,HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = null;
        Claims body = null;
        Date now = new Date();
        long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
        Date expiry = new Date(now.getTime() + EXPIRATION_TIME);
        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            body = extractToken(token);
        }
        if (body != null) {
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("companyId", companyid);
            claims.put("role", body.get("role"));
            claims.put("tokenFrom","Company_Service");

            return Jwts.builder()
                    .claims(claims)
                    .signWith(SECRET_KEY)
                    .subject(body.getSubject())
                    .issuedAt(now)
                    .expiration(expiry)
                    .compact();

        }
        throw new RuntimeException("Error while Creating Token");
    }
}
