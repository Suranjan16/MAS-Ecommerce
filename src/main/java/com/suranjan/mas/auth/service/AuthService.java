package com.suranjan.mas.auth.service;


import com.suranjan.mas.auth.entity.Role;
import com.suranjan.mas.auth.entity.User;
import com.suranjan.mas.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(User user) {

        // Check if email already exists
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Default role
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }
}
