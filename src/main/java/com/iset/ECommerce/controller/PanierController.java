package com.iset.ECommerce.controller;

import com.iset.ECommerce.entity.Panier;
import com.iset.ECommerce.metier.JwtTokenService;
import com.iset.ECommerce.metier.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paniers")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<Panier> addToCart(@RequestParam String userId, @RequestParam String produitId, @RequestParam int quantite, @RequestHeader("Authorization") String token) {
        try
        {
            if (!jwtTokenService.validateToken(token) && !jwtTokenService.getUserIdFromToken(token).equals(userId)) {
                System.out.println(token);
                System.out.println("User Not Allowed");
                return ResponseEntity.status(401).build();
            }
            System.out.println("User Allowed");
            return ResponseEntity.ok(panierService.addToCart(userId, produitId, quantite));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Panier>> getAllCartItems(@RequestHeader("Authorization") String token) {
        try {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(panierService.getAllCartItems());
        } catch (Exception e) {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Panier>> getUserCart(@PathVariable String userId, @RequestHeader("Authorization") String token) {
        try
        {
            if (!jwtTokenService.isAdmin(token) && !jwtTokenService.getUserIdFromToken(token).equals(userId)) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(panierService.getUserCart(userId));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Panier> getCartItem(@PathVariable String id, @RequestHeader("Authorization") String token) {
        try
        {
            String userId = panierService.getCartItem(id).getUserId();
            if (!jwtTokenService.isAdmin(token) && !jwtTokenService.getUserIdFromToken(token).equals(userId)) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(panierService.getCartItem(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Panier> updateCartItem(@PathVariable String id, @RequestParam int quantite, @RequestHeader("Authorization") String token) {
        try
        {
            String userId = panierService.getCartItem(id).getUserId();
            if (!jwtTokenService.isAdmin(token) && !jwtTokenService.getUserIdFromToken(token).equals(userId)) {

                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(panierService.updateCartItem(id, quantite));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable String id, @RequestHeader("Authorization") String token) {
        try
        {
            String userId = panierService.getCartItem(id).getUserId();
            if (!jwtTokenService.isAdmin(token) && !jwtTokenService.getUserIdFromToken(token).equals(userId)) {
                return ResponseEntity.status(401).build();
            }
            panierService.removeCartItem(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }

    }
}
