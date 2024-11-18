package com.iset.ECommerce.metier;

import com.iset.ECommerce.dao.IDaoPanier;
import com.iset.ECommerce.dao.IDaoProduit;
import com.iset.ECommerce.dao.IDaoUser;
import com.iset.ECommerce.entity.Panier;
import com.iset.ECommerce.entity.Produit;
import com.iset.ECommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierService {

    @Autowired
    private IDaoPanier daoPanier;

    @Autowired
    private IDaoUser daoUser;

    @Autowired
    private IDaoProduit daoProduit;

    // Add a product to the cart
    public Panier addToCart(String userId, String produitId, int quantite) {
        Produit produit = daoProduit.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit not found"));
        User user = daoUser.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Panier panier = new Panier();
        panier.setProduit(produit);
        panier.setUser(user);
        panier.setQuantite(quantite);
        return daoPanier.save(panier);
    }

    // Retrieve all items in the cart
    public List<Panier> getAllCartItems() {
        return daoPanier.findAll();
    }

    // Retrieve all items in a user's cart
    public List<Panier> getUserCart(String userId) {
        return daoPanier.findByUserId(userId);
    }

    public Panier getCartItem(String panierId) {
        return daoPanier.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
    }

    // Update the quantity of a product in the cart
    public Panier updateCartItem(String panierId, int quantite) {
        Panier panier = daoPanier.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        panier.setQuantite(quantite);
        return daoPanier.save(panier);
    }

    // Remove a product from the cart
    public void removeCartItem(String panierId) {
        daoPanier.deleteById(panierId);
    }
}
