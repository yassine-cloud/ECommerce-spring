package com.iset.ECommerce.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id; // UUID

    @NotBlank(message = "Last name must not be blank")
    private String nom;

    @NotBlank(message = "First name must not be blank")
    private String prenom;

    @NotBlank(message = "Contact must not be blank")
    private String contact;

    @Column(unique = true, nullable = false)
    private String email; // Unique constraint

    @NotBlank(message = "Password must not be blank")
    private String password; // Hashed

    @NotBlank(message = "Role must not be blank")
    private String role; // Admin or User

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonManagedReference
    private List<Panier> paniers; // One-to-Many with Panier
}
