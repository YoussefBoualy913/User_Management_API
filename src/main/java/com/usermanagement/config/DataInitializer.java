
package com.usermanagement.config;

import com.usermanagement.entity.Role;
import com.usermanagement.entity.User;
import com.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email:admin@example.com}")
    private String adminEmail;

    @Value("${app.admin.password:Admin@12345}")
    private String adminPassword;

    public DataInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initialize() {
        if (userRepository.existsByEmail(adminEmail)) {
            System.out.println("Le compte administrateur existe déjà.");
            return;
        }

        User admin = new User(
                "Administrateur",
                adminEmail,
                passwordEncoder.encode(adminPassword)
        );

        admin.setRole(Role.ADMIN);
        admin.setActive(true);

        userRepository.save(admin);

        System.out.println("Compte administrateur créé avec succès.");
    }
}
