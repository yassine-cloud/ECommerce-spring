package com.iset.ECommerce.metier;

import com.iset.ECommerce.dao.IDaoUser;
import com.iset.ECommerce.dto.AuthResponse;
import com.iset.ECommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private IDaoUser userRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // Signup: Register new user
    public User signup(User user) {
        // Hash the password before saving it
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("USER");
        userRepository.save(user);
        return user;
    }

    // Login: Authenticate the user and return JWT token
    public AuthResponse login(User user) {
        // Fetch user from the database
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + user.getEmail())); // Handle Optional properly


        // Validate user and password
        if (existingUser != null && bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Generate and return JWT token
            String token = jwtTokenService.generateToken(existingUser);
            AuthResponse authResponse = new AuthResponse(token, existingUser);
            return authResponse;
        }

        throw new RuntimeException("Invalid username or password");
    }
}
