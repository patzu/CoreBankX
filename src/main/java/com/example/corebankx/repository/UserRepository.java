package com.example.corebankx.repository;

import com.example.corebankx.model.App_User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<App_User, Long> {

    boolean existsByUsername(String username);
    // Add custom query methods if needed
}
