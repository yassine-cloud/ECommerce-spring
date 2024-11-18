package com.iset.ECommerce.dao;

import com.iset.ECommerce.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDaoPanier extends JpaRepository<Panier, String> {

    @Query("SELECT p FROM Panier p WHERE p.user.id = :userId")
    List<Panier> findByUserId(@Param("userId") String userId);
}
