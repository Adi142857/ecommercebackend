package com.print.ecommercebackend.service;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    private static final String SECRET =
            "this_is_my_super_secret_key_for_learning_spring_boot_jwt";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                SECRET.getBytes()
        );
    }

    public String extractEmail(String token) {

    return Jwts
            .parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
     }

    public String generateToken(String email) {

    return Jwts.builder()
            .subject(email)
            .issuedAt(new Date(0))
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(getSigningKey())
            .compact();
    }
}