package com.iset.ECommerce.controller;

import com.iset.ECommerce.entity.User;
import com.iset.ECommerce.metier.JwtTokenService;
import com.iset.ECommerce.metier.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user, @RequestHeader("Authorization") String token) {
        try
        {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(userService.createUser(user));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token) {
        try {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id, @RequestHeader("Authorization") String token) {

        try {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
        User getuser = userService.getAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
            if (getuser == null) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.ok(getuser);
        } catch (Exception e) {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email, @RequestHeader("Authorization") String token) {

        try {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
            User user = userService.getUserByEmail(email);
            if (user == null) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(409).build();
        }

    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user, @RequestHeader("Authorization") String token) {
        try
        {
            if (!jwtTokenService.isAdmin(token) && !jwtTokenService.getUserIdFromToken(token).equals(user.getId())) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(userService.updateUser(user));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id, @RequestHeader("Authorization") String token) {
        try
        {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }
}
