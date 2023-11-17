package com.example.applemarketplace.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface SimpleEmailSubjectUserDetails extends UserDetails {
    String getEmail();

    @Override
    default String getUsername() {
        return getEmail();
    }

    @Override
    default boolean isAccountNonExpired() {
        return true;
    }

    @Override
    default boolean isAccountNonLocked() {
        return true;
    }

    @Override
    default boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    default boolean isEnabled() {
        return true;
    }
}
