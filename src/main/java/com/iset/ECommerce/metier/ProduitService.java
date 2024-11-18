package com.iset.ECommerce.metier;

import com.iset.ECommerce.dao.IDaoCategorie;
import com.iset.ECommerce.dao.IDaoProduit;
import com.iset.ECommerce.entity.Categorie;
import com.iset.ECommerce.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private IDaoProduit daoProduit;

    @Autowired
    private IDaoCategorie daoCategorie;

    // Create a new product
    public Produit createProduit(Produit produit, String categorieId) {
        Categorie categorie = daoCategorie.findById(categorieId)
                .orElseThrow(() -> new RuntimeException("Categorie not found"));
        produit.setCategorie(categorie);
        return daoProduit.save(produit);
    }

    // Retrieve all products
    public List<Produit> getAllProduits() {
        return daoProduit.findAll();
    }

    // Get products by category ID
    public List<Produit> getProduitsByCategorieId(String categorieId) {
        return daoProduit.findByCategorieId(categorieId);
    }

    // Get a product by ID
    public Produit getProduitById(String id) {
        return daoProduit.findById(id).orElseThrow(() ->
                new RuntimeException("Produit not found"));
    }

    // Update an existing product
    public Produit updateProduit(String id, Produit updatedProduit) {
        Produit existingProduit = getProduitById(id);
        existingProduit.setDesignation(updatedProduit.getDesignation());
        existingProduit.setPrix(updatedProduit.getPrix());
        existingProduit.setPhoto(updatedProduit.getPhoto());
        return daoProduit.save(existingProduit);
    }

    // Delete a product by ID
    public void deleteProduit(String id) {
        daoProduit.deleteById(id);
    }
}
