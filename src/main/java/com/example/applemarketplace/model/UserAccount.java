package com.example.applemarketplace.model;

import com.example.applemarketplace.security.SimpleEmailSubjectUserDetails;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserAccount implements SimpleEmailSubjectUserDetails {
    private String id;
    private String password;
    private String email;
    private ClientProfile clientProfile;
    private ManagerProfile managerProfile;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (managerProfile != null) {
            authorities.add(new SimpleGrantedAuthority("MANAGER"));
        }
        if (clientProfile != null) {
            authorities.add(new SimpleGrantedAuthority("CLIENT"));
        }
        return authorities;
    }
}

