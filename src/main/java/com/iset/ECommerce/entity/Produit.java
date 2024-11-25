package com.iset.ECommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id; // UUID

    @NotBlank(message = "Product name must not be blank")
    private String designation;

    @Positive(message = "Price must be positive")
    private double prix;

    @NotBlank(message = "Photo URL must not be blank")
    private String photo; // URL

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference
    private Categorie categorie; // Many-to-One with Categorie

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "categorie")  // Only expose the name of the category, not the full object
    public String getCategorieName() {
        return categorie != null ? categorie.getId() : null;
    }
}
