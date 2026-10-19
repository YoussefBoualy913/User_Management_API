package com.usermanagement.controller;

import com.usermanagement.entity.User;
import com.usermanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.
                ok(user);

    }

}
