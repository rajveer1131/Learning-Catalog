package com.example.Auth.demo.Service;

import com.example.Auth.demo.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;


@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;
    public String generateToken(User user){

        Date currTime = new Date();
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role",user.getRole())
                .issuedAt(currTime)
                .expiration(new Date(currTime.getTime()+ expiration))
                .signWith(getSigningKey())
                .compact();

    }

    private SecretKey getSigningKey() {
            byte[ ] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
            return Keys.hmacShaKeyFor(secretBytes);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, User user){
        return (extractUsername(token).equals(user.getEmail()) && !isTokenExpired(token));
    }

}
