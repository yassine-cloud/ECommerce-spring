package com.iset.ECommerce.metier;

import com.iset.ECommerce.dao.IDaoUser;
import com.iset.ECommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private IDaoUser daoUser;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // Register a new user
    public User createUser(User user) {
        return daoUser.save(user);
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return daoUser.findAll();
    }

    // Get a user by email (e.g., for login)
    public User getUserByEmail(String email) {
        return daoUser.findByEmail(email).orElseThrow(() ->
                new RuntimeException("User not found"));
    }

    // Delete a user by ID
    public void deleteUser(String id) {
        daoUser.deleteById(id);
    }

    // Update a user
    public User updateUser(User user) {
        User existingUser = daoUser.findById(user.getId()).orElseThrow(() ->
                new RuntimeException("User not found"));
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setContact(user.getContact());
        existingUser.setEmail(user.getEmail());
        if (user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().equals(existingUser.getPassword())) {
            String password = bCryptPasswordEncoder.encode(user.getPassword());
            existingUser.setPassword(password);
        }
        existingUser.setRole(user.getRole());
        return daoUser.save(existingUser);
//        return daoUser.save(user);
    }



}
