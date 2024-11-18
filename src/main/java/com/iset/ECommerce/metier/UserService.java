package com.iset.ECommerce.metier;

import com.iset.ECommerce.dao.IDaoUser;
import com.iset.ECommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private IDaoUser daoUser;

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



}
