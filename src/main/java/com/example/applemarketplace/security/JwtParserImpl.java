package com.example.applemarketplace.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtParserImpl implements JwtParser{
    @Value("${app.security.jwt.secret}")
    private String secret;

    @Value("${app.security.jwt.lifetime}")
    private Duration lifetime;

    private static final String ROLES_CLAIMS_NAME = "roles";

    @Override
    public String generateToken(final UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(
                ROLES_CLAIMS_NAME,
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );

        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + lifetime.toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public String getUsername(final String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public List<SimpleGrantedAuthority> getRoles(final String token) {
        return ((List<String>) getClaims(token).get(ROLES_CLAIMS_NAME)).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private Claims getClaims(final String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
