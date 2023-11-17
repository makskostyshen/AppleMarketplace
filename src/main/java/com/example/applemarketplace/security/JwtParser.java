package com.example.applemarketplace.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface JwtParser {
    String generateToken(final UserDetails userDetails);

    String getUsername(final String token);

    List<SimpleGrantedAuthority> getRoles(final String token);
}
