package com.example.applemarketplace.service.model;

import com.example.applemarketplace.security.SimpleEmailSubjectUserDetails;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Data
public class UserAccount implements SimpleEmailSubjectUserDetails {
    private String id;
    private List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ADMIN"));
    private String password;
    private String email;
}

