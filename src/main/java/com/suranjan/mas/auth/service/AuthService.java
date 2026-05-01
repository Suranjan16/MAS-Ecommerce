package com.suranjan.mas.auth.service;


import com.suranjan.mas.auth.entity.Role;
import com.suranjan.mas.auth.entity.User;
import com.suranjan.mas.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public User signup(User user) {

        // Check if email already exists
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Default role
        user.setRole(Role.USER);

        return repository.save(user);
    }
}
