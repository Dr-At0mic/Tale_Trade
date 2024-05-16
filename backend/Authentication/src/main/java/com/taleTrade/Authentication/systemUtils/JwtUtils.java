package com.taleTrade.Authentication.systemUtils;

import com.taleTrade.Authentication.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUtils {
    public static String generateToken(User user, Long expirationTime){
        return Jwts.builder()
                .setClaims(extraClaims(user))
                .setSubject(user.getEmailAddress())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(Keys.hmacShaKeyFor(SystemConstants.SECRET_KEY.getBytes()),SignatureAlgorithm.HS256)
                .compact();
    }

    public static Map<String, Object> extraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("User_Id", user.getUserId());
        extraClaims.put("Account_Status", user.getAccountStatus());
        return extraClaims;
    }

    public static Claims tokenExtractor(String token) {
        return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(SystemConstants.SECRET_KEY.getBytes())).build().parseClaimsJws(token).getBody();
    }

    public static String extractUserName(String token) {
        return tokenExtractor(token).getSubject();
    }

    public static Map<String, Object> extractExtraClaims(String token) {
        Claims claims = tokenExtractor(token);
        return claims.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("sub") && !entry.getKey().equals("exp") && !entry.getKey().equals("iat"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(SystemConstants.SECRET_KEY.getBytes())).build().parseClaimsJws(token).getBody();
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

}
