package com.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/*
* This Class will handle
* Generate Token
* Validate Token
* Extract Email (Username)
* */

@Component
public class JwtUtil {

    //To Sign the token we need this. Should be >=32 chars
    private final String SECRET = "mysecretkeymysecretkeymysecretkey12345";


    //Creating the KEY object for secure cryptographic key required by JWT Library
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    //Generate Token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +1000 * 60 * 60)) //1 Hour
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Extract Email From Token
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //Check Token Expiry
    private boolean isTokenExpired(String token) {
        Date expiry = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiry.before(new Date());
    }

    //VALIDATE TOKEN
    public boolean validateToken(String token, String email) {

        final String extractedEmail = extractEmail(token);

        return (extractedEmail.equals(email) && !isTokenExpired(token) );
    }

}
