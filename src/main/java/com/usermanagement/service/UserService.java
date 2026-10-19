package com.usermanagement.service;

import com.usermanagement.entity.User;
import com.usermanagement.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public User createUser(User user) {
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur introuvable avec l'id : " + id)
                );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
