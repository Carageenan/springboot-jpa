package com.jpa.demo.utils;

import com.jpa.demo.models.User;
import com.jpa.demo.repos.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtUtils {

    @Autowired
    private UserRepository userRepository;

    private String SECRET_KEY = "secretASDASDASDASDADASDASDASDASDASDASDASDASDASDADASDASDASKHBJADSHHIBFIHASBDFIJBASJIFBAIJFBASDBJFBASJBFJASBFJABSJFNSADJFNJO"; // Ganti dengan kunci rahasia yang lebih aman

    // Menghasilkan token JWT
    public String generateToken(String data) {
        System.out.println("jalan generate token");
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, data);
    }

    // Membuat token JWT dengan payload data
    private String createToken(Map<String, Object> claims, String subject) {
        System.out.println("jalan create token " + subject);
//        return "nih";
//        byte[] decodedKey = Base64.getDecoder().decode(subject);
        return Jwts.builder()
                .setClaims(claims)               // Set klaim (kosong atau sesuai kebutuhan)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();                      // Generate token
    }

    // Mengambil data dari token JWT (dekripsi)
    public String extractData(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Mengambil klaim tertentu dari token
    public <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Mengambil semua klaim dari token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Map<String, Object> validateUser(String token) {
        token = token.substring(7);
        try {
            String unameToken = extractData(token);
            if(unameToken == null) {
                return new HashMap<>(){{
                    put("status", false);
                    put("msg", "Token invalid");
                }};
            }
            Optional<User> userDb = userRepository.findByUsername(unameToken);
            if(userDb.isPresent()) {
                return new HashMap<>(){{
                    put("status", true);
                    put("user", userDb.get());
                }};
            } else {
                return new HashMap<>(){{
                    put("status", false);
                    put("msg", "Token invalid");
                }};
            }
        } catch (Exception e) {
            System.out.println("masuk nih ya " + e.getMessage());
            String[] parts = e.getMessage().split(":");
            String ErrorMsg = parts[0].trim();
            return new HashMap<>(){{
                put("status", false);
                put("msg", "Token invalid " + ErrorMsg);
            }};
        }
    }
}
