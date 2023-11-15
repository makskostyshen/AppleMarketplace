package com.example.applemarketplace.data.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, String> {
}
