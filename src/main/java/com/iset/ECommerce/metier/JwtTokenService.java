package com.iset.ECommerce.metier;

import com.iset.ECommerce.entity.User;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenService {

    private static final String SECRET = "a-secure-and-long-enough-key-for-jwt";

    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    private static final JwtParser PARSER;

    static {
        PARSER = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build();
    }

//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    private long expirationTime = 86400000; // 24 hours in milliseconds

    // Generate JWT token
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            token = token.split(" ")[1];
            if (token == null) {
                return false;
            }
            PARSER.parseClaimsJws(token);
//            Jwts.parser()
//                    .setSigningKey(secretKey)
//                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Get user ID from JWT token
    public String getUserIdFromToken(String token) {

        try {
            token = token.split(" ")[1];
            if (token == null) {
                return null;
            }
            return PARSER.parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    // Get user role from JWT token
    public String getUserRoleFromToken(String token) {
        try {
            token = token.split(" ")[1];
            if (token == null) {
                return null;
            }
            return PARSER.parseClaimsJws(token)
                    .getBody()
                    .get("role", String.class);
//            System.out.println("Role: " + role);
//            return role;
        } catch (Exception e) {
            return null;
        }
    }

    // is the role of the user in the token is admin
    public boolean isAdmin(String token) {
        return getUserRoleFromToken(token).equals("ADMIN");
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

//        if (header != null ) {
//            return header.split(" ")[1]; // Remove "Bearer " prefix and return token
//        }
        return header;
    }
}
