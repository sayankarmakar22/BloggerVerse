package com.sayan.BlogApplication.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final String secretToken = "f468t134fewuyfgewuifcbueiwqhf34ui2348965723095truyewqhbfcjkewhfoiewqhgfsi534897640923tuy42";

    private final long expiresTime = 60000*60*5L;

    public Claims parseToken(String token){
        JwtParser jwtParser = Jwts.
                parserBuilder()
                .setSigningKey(secretToken.getBytes())
                .build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token){
        return parseToken(token) != null;
    }

    public String getUsernameFromToken(String token){
        Claims claims = parseToken(token);
        if (claims != null){
            return claims.getSubject();
        }
        return null;
    }

    public String generateToken(String username){
        Key key = Keys.hmacShaKeyFor(secretToken.getBytes());
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + expiresTime);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .setIssuedAt(currentDate)
                .signWith(key)
                .compact();
    }
}
