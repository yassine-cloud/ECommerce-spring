package com.iset.ECommerce.dao;

import com.iset.ECommerce.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDaoCategorie extends JpaRepository<Categorie, String> {
}
