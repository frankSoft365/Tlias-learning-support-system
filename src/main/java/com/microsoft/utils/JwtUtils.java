package com.microsoft.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET_KEY = "ZnJhbmtTb2Z0ZnJhbmtTb2Z0ZnJhbmtTb2Z0";
    private static final long EXPIRATION_TIME = 12 * 3600 * 1000;
    public static String generateToken(Map<String, Object> dataMap) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        return Jwts.
                builder().
                signWith(key).
                claims(dataMap).
                expiration(expirationDate).
                compact();
    }
}
