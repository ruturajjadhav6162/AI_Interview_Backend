package com.job.service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    private final String Secret="my-super-secret-key-that-is-long-enough-1234567898!@#";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Secret.getBytes());

    public Claims extractToken(String token) {
//
//        Date now = new Date();
//        long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
//        Date expiry = new Date(now.getTime() + EXPIRATION_TIME);
        Claims body = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return body;
    }
    public boolean validateToken(String token) {
        return !expiration(token);
    }

    private boolean expiration(String token) {
        Claims body = extractToken(token);
        return body.getExpiration().before(new Date());
    }
}
