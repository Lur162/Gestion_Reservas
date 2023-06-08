package com.reservas.Reservas_BD.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtils {
    private final Key jwtSecret = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    private final int jwtExpirationMs = 86400000; // 24 horas

    public String generateJwtToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String token, String correo) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject().equals(correo);
        } catch (Exception e) {
            return false;
        }
    }
}
