package com.soom.account_api.global.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

public class JwtUtil {
    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;

    public static String encode(String secret, Long expiredSeconds, Map<String, Object> claims) {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime expiredDate = now.plusSeconds(expiredSeconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(expiredDate))
                .signWith(ALGORITHM, secret)
                .compact();
    }

    public static Claims decode(String secret, String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
