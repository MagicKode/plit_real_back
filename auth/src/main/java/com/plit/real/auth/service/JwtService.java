package com.plit.real.auth.service;

import com.sun.jdi.PrimitiveValue;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.KeyFactory;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KET = "mySuperSecretKeyForPlitRealProject1234567890";
    private static final long EXPIRATION_TIME = 864_000_000;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KET.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}
