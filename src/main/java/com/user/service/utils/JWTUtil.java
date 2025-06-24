package com.user.service.utils;

import com.user.service.repository.UserDetaiilsRepsitory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Component
public class JWTUtil {
    private final String Secret="my-super-secret-key-that-is-long-enough-1234567898!@#";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Secret.getBytes());
    @Autowired
    UserDetaiilsRepsitory userRepository;
    public String generateToken(String username) {

        Date now = new Date();
        long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
        Date expiry = new Date(now.getTime() + EXPIRATION_TIME);
        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("role", userRepository.findByUsername(username).get().getRole());
        claims.put("tokenFrom","User_Service");
        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .issuedAt(now)
                .expiration(expiry)
                .signWith( SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        Claims body= extractClaims(token);
        return body.getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validate(String username, String username1,String token) {
        return username.equals(username1) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Claims body = extractClaims(token);
        System.out.println(body.getExpiration());
        return body.getExpiration().before(new Date());
    }

}
