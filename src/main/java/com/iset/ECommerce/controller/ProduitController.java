package com.iset.ECommerce.controller;

import com.iset.ECommerce.entity.Produit;
import com.iset.ECommerce.metier.JwtTokenService;
import com.iset.ECommerce.metier.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestParam String categorieId, @RequestBody @Valid Produit produit, @RequestHeader("Authorization") String token) {
        try
        {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
            return ResponseEntity.ok(produitService.createProduit(produit, categorieId));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public Produit getProduitById(@PathVariable String id) {
        return produitService.getProduitById(id);
    }

    @GetMapping("/categorie/{categorieId}")
    public List<Produit> getProduitsByCategorieId(@PathVariable String categorieId) {
        return produitService.getProduitsByCategorieId(categorieId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable String id, @RequestBody @Valid Produit produit, @RequestHeader("Authorization") String token) {
        try
        {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
            if (produitService.getProduitById(id) == null) {
                return ResponseEntity.status(404).build();
            }

            return ResponseEntity.ok(produitService.updateProduit(id, produit));

        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable String id, @RequestHeader("Authorization") String token) {
        try
        {
            if (!jwtTokenService.isAdmin(token)) {
                return ResponseEntity.status(401).build();
            }
            produitService.deleteProduit(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(409).build();
        }
    }
}
