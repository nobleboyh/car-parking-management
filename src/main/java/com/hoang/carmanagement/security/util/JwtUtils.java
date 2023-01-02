package com.hoang.carmanagement.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Scope("prototype")
public class JwtUtils {
    private static final String privateKey = "Hoang";

    private String token;

    private Claims claims;

    public JwtUtils(String token) {
        this.token = token;
        this.extractAllClaims();
    }

    public JwtUtils() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        this.extractAllClaims();
    }

    public Claims getClaims() {
        return claims;
    }

    /*
    Methods
     */

    /**
     * Extract all claims
     */
    public void extractAllClaims(){
        this.claims =  Jwts.parser().setSigningKey(privateKey).parseClaimsJws(token).getBody();
    }

    /**
     * Extract subject
     * @return subject (normally email/username)
     */
    public String extractUsername(){
        return claims.getSubject();
    }

    /**
     * Get expiration date
     * @return Token expiration date
     */
    public Date extractExpirationDate(){
        return claims.getExpiration();
    }

    /**
     * Check if token still legit
     * @return boolean
     */
    public boolean isExpiredToken(){
        return extractExpirationDate().before(new Date());
    }

    public boolean isValidToken(UserDetails userDetails){
        return !isExpiredToken() && extractUsername().equals(userDetails.getUsername());
    }

    /**
     * Generate token
     * @param claims: exist claims
     * @param userDetails: user info
     * @return jwtToken
     */
    private String createToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256, privateKey).compact();

    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> objectMap = new HashMap<>();
        return createToken(objectMap, userDetails);
    }

    public String generateToken(Claims claims, UserDetails userDetails){
        return createToken(claims, userDetails);
    }
}
