package com.example.applemarketplace.service.user;

import com.example.applemarketplace.model.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends UserDetailsService {
    UserAccount getUserByEmail(String email);

    @Override
    default UserDetails loadUserByUsername(final String username) {
        return getUserByEmail(username);
    }
}
