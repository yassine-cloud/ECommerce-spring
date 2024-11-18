package com.iset.ECommerce.controller;

import com.iset.ECommerce.dto.AuthResponse;
import com.iset.ECommerce.entity.User;
import com.iset.ECommerce.metier.AuthService;
import com.iset.ECommerce.metier.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenService jwtTokenService;

//    verify the client have a valid token
    @GetMapping
    public ResponseEntity<String> verifyToken(@RequestHeader("Authorization") String token) {
        try {
            System.out.println(token);
//            boolean res = jwtTokenService.validateToken(token);
            String res = jwtTokenService.getUserRoleFromToken(token);
            if (res != null) return ResponseEntity.ok(res);
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    // Signup: Create a new user
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody User user) {

        try {

        user = authService.signup(user);

        // Generate token for the newly registered user
        String token = jwtTokenService.generateToken(user);

        // Return token and user details in the response
        AuthResponse authResponse = new AuthResponse(token, user);

        return ResponseEntity.ok(authResponse);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

    // Login: Authenticate user and return JWT token
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) {
        try
        {
            AuthResponse authResponse = authService.login(user);
            return ResponseEntity.ok(authResponse);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(401).build();
        }
    }

}
