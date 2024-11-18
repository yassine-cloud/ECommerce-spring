package com.iset.ECommerce.dao;

import com.iset.ECommerce.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDaoProduit extends JpaRepository<Produit, String> {

    List<Produit> findByCategorieId(String categorieId);
}
