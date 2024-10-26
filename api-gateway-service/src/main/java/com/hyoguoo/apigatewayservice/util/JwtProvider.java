package com.hyoguoo.apigatewayservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final Key accessSecretKey;

    public JwtProvider(@Value("${jwt.access-secret}") String accessSecretKey) {
        this.accessSecretKey = Keys.hmacShaKeyFor(accessSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public Long getUserIdFromAccessToken(String token) {
        Jws<Claims> claims = parseToken(token, accessSecretKey);

        return Long.parseLong(claims.getBody().getSubject());
    }

    private Jws<Claims> parseToken(String token, Key secretKey) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, e.getClaims(), "Token is expired");
        } catch (Exception e) {
            throw new JwtException("Token is invalid");
        }
    }
}
