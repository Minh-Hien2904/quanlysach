package com.example.quanlysach.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtService {

    private final SecretKey secretKey;

    // ✅ Nhận secret từ application.properties và tạo SecretKey chuẩn
    public JwtService(@Value("${jwt.secret}") String secretKeyString) {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    // ✅ Tạo JWT với username và roles
    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1 giờ
                .signWith(secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        Object roles = extractAllClaims(token).get("roles");
        if (roles instanceof List<?>) {
            return ((List<?>) roles).stream()
                    .map(Object::toString)
                    .toList();
        }
        return Collections.emptyList();
    }

    // ✅ Phân tích token và trích xuất claims
    private Claims extractAllClaims(String token) {
        JwtParser parser = Jwts.parser()
                .verifyWith(secretKey)
                .build();

        return parser.parseSignedClaims(token).getPayload();
    }

    // ✅ Kiểm tra tính hợp lệ của token
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("❌ Token hết hạn: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("❌ Token không hỗ trợ: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("❌ Token sai định dạng: " + e.getMessage());
        } catch (SecurityException | SignatureException e) {
            System.out.println("❌ Chữ ký không hợp lệ: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Token rỗng hoặc sai: " + e.getMessage());
        }
        return false;
    }
}
