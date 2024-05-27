package com.example.corebankx.service;

import com.example.corebankx.exception.UserRegistrationException;
import com.example.corebankx.model.App_User;
import com.example.corebankx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(App_User appUser) {
        // Check for conditions that may result in registration failure
        if (userRepository.existsByUsername(appUser.getUsername())) {
            throw new UserRegistrationException("Username already exists");
        }

        // Encode the password before saving
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        // Save the user
        userRepository.save(appUser);
    }
}
