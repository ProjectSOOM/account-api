package com.soom.account_api.global.jwt.util;

import com.soom.account_api.global.jwt.exception.JwtDecodeException;
import com.soom.account_api.global.jwt.exception.JwtEncodeException;
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

        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(Timestamp.valueOf(now))
                    .setExpiration(Timestamp.valueOf(expiredDate))
                    .signWith(ALGORITHM, secret)
                    .compact();
        } catch (Exception e) {
            throw new JwtEncodeException(e);
        }
    }

    public static Claims decode(String secret, String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new JwtDecodeException(e);
        }
    }
}
