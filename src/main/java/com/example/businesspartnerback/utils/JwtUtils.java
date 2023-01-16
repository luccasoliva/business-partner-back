package com.example.businesspartnerback.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;


@Component
public class JwtUtils {

    private static final String SECRET_KEY = "ASxc1DA21gwrcadasdASDasd1q412asDA212";
    private static final Long EXPIRATION_TIME = 2592000 * 1000L;

    public static String createToken(String email){
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (Exception e){
            return null;
        }
    }
}

