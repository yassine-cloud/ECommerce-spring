package com.iset.ECommerce.controller;

import com.iset.ECommerce.entity.Categorie;
import com.iset.ECommerce.metier.CategorieService;
import com.iset.ECommerce.metier.JwtTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody @Valid Categorie categorie, @RequestHeader("Authorization") String token) {
        try
        {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(categorieService.createCategorie(categorie));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }

    }

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Categorie getCategorieById(@PathVariable String id) {
        return categorieService.getCategorieById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable String id, @RequestBody @Valid Categorie categorie, @RequestHeader(value = "Authorization", required = true ) String token) {
        try
        {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(categorieService.updateCategorie(id, categorie));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable String id, @RequestHeader("Authorization") String token) {
        try
        {
            if (!jwtTokenService.isAdmin(token)) {
                ResponseEntity.status(401).build();
            }
            categorieService.deleteCategorie(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

}
