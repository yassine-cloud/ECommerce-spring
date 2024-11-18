package com.iset.ECommerce.metier;

import com.iset.ECommerce.dao.IDaoCategorie;
import com.iset.ECommerce.entity.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private IDaoCategorie daoCategorie;

    // Create a new category
    public Categorie createCategorie(Categorie categorie) {
        return daoCategorie.save(categorie);
    }

    // Retrieve all categories
    public List<Categorie> getAllCategories() {
        return daoCategorie.findAll();
    }

    // Get a category by ID
    public Categorie getCategorieById(String id) {
        return daoCategorie.findById(id).orElseThrow(() ->
                new RuntimeException("Categorie not found"));
    }

    // Update an existing category
    public Categorie updateCategorie(String id, Categorie updatedCategorie) {
        Categorie existingCategorie = getCategorieById(id);
        existingCategorie.setNom(updatedCategorie.getNom());
        existingCategorie.setPhoto(updatedCategorie.getPhoto());
        return daoCategorie.save(existingCategorie);
    }

    // Delete a category by ID
    public void deleteCategorie(String id) {
        daoCategorie.deleteById(id);
    }
}
